package me.theek.memox.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.theek.memox.feature.home.components.NotesListView

@Composable
fun HomeScreen(
    onNavigateToEmptyScreen: () -> Unit,
    onNavigateToNoteScreen: (Long?) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val notesState by homeViewModel.noteState.collectAsStateWithLifecycle()
    val selectedFolderState by homeViewModel.selectedNotesStream.collectAsStateWithLifecycle()

    NotesListView(
        notesState = notesState,
        onFolderClick = homeViewModel::onFolderClick,
        onNoteClick = onNavigateToNoteScreen,
        onNoteDelete = homeViewModel::onNoteDelete,
        onNewNoteCreateClick = onNavigateToNoteScreen,
        onNavigateToEmptyScreen = onNavigateToEmptyScreen
    )

    AnimatedVisibility(
        visible = homeViewModel.shouldShowFolderScreen,
        enter = slideInHorizontally(),
        exit =  slideOutHorizontally()
    ) {
        FolderFilterScreen(
            selectedFolderState = selectedFolderState,
            newFolderName = homeViewModel.newFolderName,
            shouldShowFolderRenameDialog = homeViewModel.shouldShowFolderRenameDialog,
            shouldShowFolderDeleteDialog = homeViewModel.shouldShowFolderDeleteDialog,
            onShowFolderRenameDialog = homeViewModel::onShowFolderRenameDialog,
            onDismissFolderRenameDialog = homeViewModel::onDismissFolderRenameDialog,
            onShowFolderDeleteDialog = homeViewModel::onShowFolderDeleteDialog,
            onDismissFolderDeleteDialog = homeViewModel::onDismissFolderDeleteDialog,
            onFolderNameChange = homeViewModel::onFolderNameChange,
            onNoteClick = onNavigateToNoteScreen,
            onNoteDelete = homeViewModel::onNoteDelete,
            onFolderDelete = homeViewModel::onFolderDelete,
            onFolderRename = homeViewModel::onFolderRename,
            validationResult = homeViewModel.folderValidationState,
            onBackPress = homeViewModel::onHideFolderScreen
        )
    }
}