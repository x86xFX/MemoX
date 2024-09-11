package me.theek.memox.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.theek.memox.core.domain.repository.FolderRepository
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.model.Folder
import me.theek.memox.core.model.NoteWithPhotosAndFolder
import me.theek.memox.core.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    noteRepository: NoteRepository,
    folderRepository: FolderRepository
) : ViewModel() {

    val noteState: StateFlow<UiState<List<NoteWithPhotosAndFolder>>> = noteRepository.getAllNotes()
        .map { UiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    val folders: StateFlow<UiState<List<Folder>>> = folderRepository.getAllFolders()
        .map { UiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )
}