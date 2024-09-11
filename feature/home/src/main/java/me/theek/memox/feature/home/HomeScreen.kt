package me.theek.memox.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.theek.memox.feature.home.components.NotesListView

@Composable
fun HomeScreen(
    onNavigateToEmptyScreen: () -> Unit,
    onNavigateToNoteScreen: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val notesState by homeViewModel.noteState.collectAsStateWithLifecycle()
    val foldersState by homeViewModel.folders.collectAsStateWithLifecycle()

    NotesListView(
        notesState = notesState,
        foldersState = foldersState,
        onFolderClick = {},
        onNoteClick = {},
        onNewNoteCreateClick = onNavigateToNoteScreen,
        onNavigateToEmptyScreen = onNavigateToEmptyScreen
    )
}