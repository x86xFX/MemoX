package me.theek.memox.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.util.fastDistinctBy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.theek.memox.core.domain.repository.FolderRepository
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.domain.use_case.ValidateFolderName
import me.theek.memox.core.domain.use_case.ValidationResult
import me.theek.memox.core.model.Folder
import me.theek.memox.core.model.NoteWithPhotosAndFolder
import me.theek.memox.core.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val folderRepository: FolderRepository,
    private val validateFolderName: ValidateFolderName
) : ViewModel() {

    val noteState: StateFlow<UiState<Pair<List<NoteWithPhotosAndFolder>, List<Folder>>>> = noteRepository.getAllNotes()
        .map { notesWithPhotosAndFolder ->
            UiState.Success(
                Pair(
                    first = notesWithPhotosAndFolder,
                    second = notesWithPhotosAndFolder
                        .fastDistinctBy { it.folder.id }
                        .map { it.folder }
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    private val _selectedNotesStream = MutableStateFlow<UiState<List<NoteWithPhotosAndFolder>>>(UiState.Loading)
    val selectedNotesStream: StateFlow<UiState<List<NoteWithPhotosAndFolder>>> = _selectedNotesStream.asStateFlow()
    var folderValidationState: ValidationResult by mutableStateOf(ValidationResult(isValid = true))
        private set
    var newFolderName by mutableStateOf("")
        private set
    var shouldShowFolderScreen by mutableStateOf(false)
        private set
    var shouldShowFolderRenameDialog by mutableStateOf(false)
        private set
    var shouldShowFolderDeleteDialog by mutableStateOf(false)
        private set

    fun onNoteDelete(noteId: Long) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId = noteId)
        }
    }

    fun onFolderClick(folderId: Long) {
        viewModelScope.launch {
            when (noteState.value) {
                UiState.Loading -> {
                    _selectedNotesStream.value = UiState.Loading
                }
                is UiState.Success -> {
                    val filteredNotes =
                        (noteState.value as UiState.Success<Pair<List<NoteWithPhotosAndFolder>, List<Folder>>>)
                            .data
                            .first
                            .filter { it.folder.id == folderId }
                    _selectedNotesStream.value = UiState.Success(filteredNotes)
                    shouldShowFolderScreen = true
                }
            }
        }
    }

    fun onFolderNameChange(value: String) {
        newFolderName = value
    }

    fun onFolderDelete(folder: Folder) {
        viewModelScope.launch {
            folderRepository.deleteFolder(folder)
            onDismissFolderDeleteDialog()
        }
    }

    fun onFolderRename(folder: Folder) {
        viewModelScope.launch {
            val validationResult = validateFolderName(folderName = newFolderName)

            if (validationResult.isValid) {
                folderRepository.updateFolder(
                    folder = folder.copy(
                        name = newFolderName,
                        modifiedDate = System.currentTimeMillis()
                    )
                )
                onDismissFolderRenameDialog()
                newFolderName = ""
                shouldShowFolderScreen = false
                folderValidationState = ValidationResult(isValid = true)
            } else {
                folderValidationState = validationResult
            }
        }
    }

    fun onDismissFolderRenameDialog() { shouldShowFolderRenameDialog = false }

    fun onShowFolderRenameDialog() { shouldShowFolderRenameDialog = true }

    fun onDismissFolderDeleteDialog() { shouldShowFolderDeleteDialog = false }

    fun onShowFolderDeleteDialog() { shouldShowFolderDeleteDialog = true }

    fun onHideFolderScreen() { shouldShowFolderScreen = false }
}