package me.theek.memox.feature.note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.theek.memox.feature.camera.CameraScreen
import me.theek.memox.feature.location.LocationScreen
import me.theek.memox.feature.note.components.AdditionalFeaturesDialog
import me.theek.memox.feature.note.components.FoldersRow
import me.theek.memox.feature.note.components.NewFolderDialog
import me.theek.memox.feature.note.components.NoteTextArea
import me.theek.memox.feature.note.components.NoteTopAppBar

@Composable
fun NoteCreationScreen(
    onNavigateBack: () -> Unit,
    onCurrentLocationClick: () -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel()
) {
    val foldersState by noteViewModel.folders.collectAsStateWithLifecycle()
    val cameraPermissionState by noteViewModel.cameraPermission.collectAsStateWithLifecycle()
    val locationPermissionState by noteViewModel.locationPermission.collectAsStateWithLifecycle()
    val locationStream by noteViewModel.locationStream.collectAsStateWithLifecycle()
    var shouldShowAdditionalFeaturesDialog by remember { mutableStateOf(false) }
    var shouldShowCameraView by rememberSaveable { mutableStateOf(false) }
    var shouldShowLocationView by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            NoteTopAppBar(
                onSaveNoteClick = noteViewModel::onNewNoteCreate,
                onNavigateBack = onNavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton (
                modifier = Modifier.imePadding(),
                onClick = {
                    focusManager.clearFocus(force = true)
                    shouldShowAdditionalFeaturesDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FoldersRow(
                    selectedFolderId = noteViewModel.selectedFolderId,
                    isEmptySelectedFolder = noteViewModel.isEmptySelectedFolder,
                    foldersState = foldersState,
                    onNewFolderCreateClick = { noteViewModel.onShowNewFolderDialog(show = true) },
                    onFolderSelect = noteViewModel::onFolderSelect
                )

                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    NoteTextArea(
                        maxHeight = this.maxHeight,
                        title = noteViewModel.noteTitle,
                        description = noteViewModel.noteDescription,
                        titleValidationResult = noteViewModel.noteTitleValidationState,
                        onTitleChange = noteViewModel::onNoteTitleChange,
                        onDescriptionChange = noteViewModel::onNoteDescriptionChange,
                        selectedPics = noteViewModel.selectedPics
                    )
                }
            }
        }
    )

    if (shouldShowAdditionalFeaturesDialog) {
        AdditionalFeaturesDialog(
            onDismissRequest = { shouldShowAdditionalFeaturesDialog = false },
            onAddPhotosClick = {
                shouldShowAdditionalFeaturesDialog = false
                shouldShowCameraView = true
            },
            onAddLocationClick = {
                shouldShowAdditionalFeaturesDialog = false
                shouldShowLocationView = true
            }
        )
    }

    if (noteViewModel.shouldShowNewFolderDialog) {
        NewFolderDialog(
            folderName = noteViewModel.newFolderName,
            validationResult = noteViewModel.folderValidationState,
            onFolderNameChange = noteViewModel::onNewFolderNameChange,
            onDismissRequest = { noteViewModel.onShowNewFolderDialog(show = false) },
            onFolderCreate = noteViewModel::onNewFolderCreate,
        )
    }

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = shouldShowCameraView,
        enter = expandHorizontally(),
        exit = shrinkHorizontally(),
        label = "cameraView"
    ) {
        CameraScreen(
            permissionState = cameraPermissionState,
            onCameraPermissionCheck = noteViewModel::checkCameraPermissions,
            onPhotosPick = noteViewModel::onPhotosPick,
            onBackPress = { shouldShowCameraView = false }
        )
    }

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = shouldShowLocationView,
        enter = expandHorizontally(),
        exit = shrinkHorizontally(),
        label = "locationView"
    ) {
        LocationScreen(
            locationStream = locationStream,
            permissionState = locationPermissionState,
            onLocationPermissionCheck = noteViewModel::checkLocationPermissions,
            currentLocation = noteViewModel.currentLocation,
            onCurrentLocationClick = onCurrentLocationClick
        )
    }

    LaunchedEffect(key1 = noteViewModel.shouldNavigateToHome) {
        if (noteViewModel.shouldNavigateToHome) {
            onNavigateBack()
        }
    }
}