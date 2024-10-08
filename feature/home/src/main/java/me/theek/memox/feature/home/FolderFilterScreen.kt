package me.theek.memox.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.icons.Create_new_folder
import me.theek.memox.core.design_system.icons.History
import me.theek.memox.core.design_system.ui.theme.K2DFontFamily
import me.theek.memox.core.domain.use_case.ValidationResult
import me.theek.memox.core.model.Folder
import me.theek.memox.core.model.NoteWithPhotosAndFolder
import me.theek.memox.core.util.UiState
import me.theek.memox.feature.home.components.NoteRow
import me.theek.memox.feature.home.util.toReadableTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FolderFilterScreen(
    selectedFolderState: UiState<List<NoteWithPhotosAndFolder>>,
    newFolderName: String,
    validationResult: ValidationResult,
    shouldShowFolderRenameDialog: Boolean,
    shouldShowFolderDeleteDialog: Boolean,
    onShowFolderRenameDialog: () -> Unit,
    onDismissFolderRenameDialog: () -> Unit,
    onShowFolderDeleteDialog: () -> Unit,
    onDismissFolderDeleteDialog: () -> Unit,
    onFolderNameChange: (String) -> Unit,
    onNoteClick: (Long) -> Unit,
    onNoteDelete: (Long) -> Unit,
    onFolderRename: (Folder) -> Unit,
    onFolderDelete: (Folder) -> Unit,
    onBackPress: () -> Unit
) {
    when(selectedFolderState) {
        UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                        title = {
                            Text(
                                text = selectedFolderState.data.first().folder.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontFamily = K2DFontFamily,
                                fontWeight = FontWeight.Light,
                                style = TextStyle(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.colorScheme.tertiary,
                                            MaterialTheme.colorScheme.tertiary,
                                        )
                                    )
                                )
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBackPress) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = stringResource(R.string.navigate_back)
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = onShowFolderRenameDialog) {
                                Icon(
                                    imageVector = Icons.Rounded.Edit,
                                    contentDescription = stringResource(R.string.rename_folder)
                                )
                            }

                            IconButton(onClick = onShowFolderDeleteDialog) {
                                Icon(
                                    imageVector = Icons.Rounded.Delete,
                                    contentDescription = stringResource(R.string.delete_folder)
                                )
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Create_new_folder,
                                    contentDescription = stringResource(R.string.folder_created_at)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 5.dp),
                                    text = selectedFolderState.data.first().folder.createdDate.toReadableTime(),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    fontFamily = K2DFontFamily,
                                    fontWeight = FontWeight.Light,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = History,
                                    contentDescription = stringResource(R.string.folder_modified_at)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 5.dp),
                                    text = selectedFolderState.data.first().folder.modifiedDate.toReadableTime(),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    fontFamily = K2DFontFamily,
                                    fontWeight = FontWeight.Light,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            items(
                                items = selectedFolderState.data,
                                key = { it.note.id }
                            ) { note ->
                                NoteRow(
                                    modifier = Modifier.padding(horizontal = 5.dp),
                                    note = note.note,
                                    photo = note.photo,
                                    folderName = note.folder.name,
                                    onNoteClick = onNoteClick,
                                    onNoteDelete = onNoteDelete,
                                )
                            }
                        }
                    }

                    FolderAlerts(
                        shouldShowFolderRenameDialog = shouldShowFolderRenameDialog,
                        shouldShowFolderDeleteWarning = shouldShowFolderDeleteDialog,
                        folder = selectedFolderState.data.first().folder,
                        newFolderName = newFolderName,
                        validationResult = validationResult,
                        onFolderNameChange = onFolderNameChange,
                        onFolderRename = onFolderRename,
                        onFolderDeleteAlertDismiss = onDismissFolderDeleteDialog,
                        onFolderRenameAlertDismiss = onDismissFolderRenameDialog,
                        onFolderDelete = onFolderDelete
                    )
                }
            )

        }
    }

    BackHandler {
        onBackPress()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FolderAlerts(
    modifier: Modifier = Modifier,
    shouldShowFolderRenameDialog: Boolean,
    shouldShowFolderDeleteWarning: Boolean,
    folder: Folder,
    newFolderName: String,
    validationResult: ValidationResult,
    onFolderNameChange: (String) -> Unit,
    onFolderRename: (Folder) -> Unit,
    onFolderRenameAlertDismiss: () -> Unit = {},
    onFolderDeleteAlertDismiss: () -> Unit = {},
    onFolderDelete: (Folder) -> Unit
) {
    if (shouldShowFolderRenameDialog) {
        BasicAlertDialog(
            onDismissRequest = onFolderRenameAlertDismiss,
            content = {
                Column(
                    modifier = modifier
                        .clip(AlertDialogDefaults.shape)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically)
                ) {
                    Text(
                        text = "Rename folder",
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        fontFamily = K2DFontFamily,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = newFolderName,
                        onValueChange = onFolderNameChange,
                        label = {
                            Text(text = stringResource(R.string.folder_name))
                        },
                        isError = validationResult.isValid.not(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { onFolderRename(folder) }
                        ),
                        supportingText = {
                            if (validationResult.isValid.not() && validationResult.errorMessage != null) {
                                Text(
                                    text = validationResult.errorMessage!!,
                                    color = MaterialTheme.colorScheme.error,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        FilledTonalButton (onClick = { onFolderRename(folder) }) {
                            Text(text = stringResource(R.string.save))
                        }
                    }
                }
            }
        )
    }

    if (shouldShowFolderDeleteWarning) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = onFolderDeleteAlertDismiss,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = stringResource(R.string.delete_folder_warning),
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            },
            title = {
                Text(
                    text = stringResource(R.string.delete_folder),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            },
            text = {
                Text(
                    text = buildAnnotatedString {
                        append("By clicking yes, you are going to permanently delete", " ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(folder.name)
                        }
                        append(" ", "folder. This action also", " ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("permanently delete")
                        }
                        append(" ", "all the linked notes with this folder.", "You won't able to recover them later.")
                    },
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            },
            dismissButton = {
                TextButton(
                    onClick = onFolderDeleteAlertDismiss,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onErrorContainer)
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            confirmButton = {
                Button (
                    onClick = { onFolderDelete(folder) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onErrorContainer,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            }
        )
    }
}
