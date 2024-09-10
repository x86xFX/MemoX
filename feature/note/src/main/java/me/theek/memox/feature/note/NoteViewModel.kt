package me.theek.memox.feature.note

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.theek.memox.core.domain.repository.CameraRepository
import me.theek.memox.core.domain.repository.FolderRepository
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.domain.use_case.ValidateFolderName
import me.theek.memox.core.domain.use_case.ValidateNoteTitle
import me.theek.memox.core.domain.use_case.ValidationResult
import me.theek.memox.core.model.Folder
import me.theek.memox.core.model.Note
import me.theek.memox.core.model.Photo
import me.theek.memox.feature.camera.CameraPermissionState
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val folderRepository: FolderRepository,
    private val cameraRepository: CameraRepository,
    private val validateFolderName: ValidateFolderName,
    private val validateNoteTitle: ValidateNoteTitle
) : ViewModel() {

    private val _cameraPermission: MutableStateFlow<CameraPermissionState> = MutableStateFlow(CameraPermissionState.Loading)
    val cameraPermission: StateFlow<CameraPermissionState> = _cameraPermission.asStateFlow()

    val folders: StateFlow<UiState<List<Folder>>> = folderRepository.getAllFolders()
        .map {
            delay(3000)
            if (it.isEmpty()) {
                UiState.Empty
            } else {
                UiState.Success(it)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    var noteTitle by mutableStateOf("")
        private set
    var noteDescription by mutableStateOf("")
        private set
    var shouldShowNewFolderDialog by mutableStateOf(false)
        private set
    var isEmptySelectedFolder by mutableStateOf(false)
        private set
    var newFolderName by mutableStateOf("")
        private set
    var selectedFolderId by mutableStateOf<Long?>(null)
        private set
    var selectedPics by mutableStateOf<List<Uri>?>(null)
        private set
    var folderValidationState: ValidationResult by mutableStateOf(ValidationResult(isValid = true))
        private set
    var noteTitleValidationState: ValidationResult by mutableStateOf(ValidationResult(isValid = true))
        private set

    fun checkCameraPermissions() {
        viewModelScope.launch {
            if (cameraRepository.checkCameraPermission().single()) {
                _cameraPermission.value = CameraPermissionState.PermissionGranted
            } else {
                _cameraPermission.value = CameraPermissionState.PermissionDenied
            }
        }
    }

    fun onNoteTitleChange(value: String) {
        noteTitle = value
    }
    fun onNoteDescriptionChange(value: String) {
        noteDescription = value
    }
    fun onFolderSelect(folderId: Long) {
        selectedFolderId = folderId
    }
    fun onShowNewFolderDialog(show: Boolean) {
        shouldShowNewFolderDialog = show
    }
    fun onNewFolderNameChange(value: String) {
        newFolderName = value
    }

    fun onNewNoteCreate() {
        viewModelScope.launch {
            if (selectedFolderId != null) {
                val validationResult = validateNoteTitle(noteTitle = noteTitle)

                if (validationResult.isValid) {
                    noteRepository.createNote(
                        note = Note(
                            title = noteTitle,
                            description = noteDescription,
                            folderId = selectedFolderId!!,
                            modifiedDate = System.currentTimeMillis(),
                            createdDate = System.currentTimeMillis()
                        ),
                        pics = selectedPics?.map { Photo(path = it, capturedAt = System.currentTimeMillis()) },
                    )
                } else {
                    noteTitleValidationState = validationResult
                }
            } else {
                isEmptySelectedFolder = true
            }
        }
    }

    fun onNewFolderCreate() {
        viewModelScope.launch {

            val validationResult = validateFolderName(folderName = newFolderName)

            if (validationResult.isValid) {
                val createdTime = System.currentTimeMillis()
                folderRepository.createNewFolder(
                    folder = Folder(
                        name = newFolderName,
                        modifiedDate = createdTime,
                        createdDate = createdTime
                    )
                )
                onShowNewFolderDialog(show = false)
                newFolderName = ""

            } else {
                println(validationResult)
                folderValidationState = validationResult
            }
        }
    }

    fun onPhotosPick(pics: List<Uri>) {
        selectedPics = pics
    }
}