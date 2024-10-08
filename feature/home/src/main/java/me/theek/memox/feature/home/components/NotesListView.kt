package me.theek.memox.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.components.shimmer
import me.theek.memox.core.design_system.ui.theme.K2DFontFamily
import me.theek.memox.core.model.Folder
import me.theek.memox.core.model.NoteWithPhotosAndFolder
import me.theek.memox.core.util.UiState
import me.theek.memox.feature.home.R
import me.theek.memox.feature.home.util.getGreeting

@Composable
internal fun NotesListView(
    notesState: UiState<Pair<List<NoteWithPhotosAndFolder>, List<Folder>>>,
    onNewNoteCreateClick: (Long?) -> Unit,
    onNoteClick: (Long) -> Unit,
    onNoteDelete: (Long) -> Unit,
    onFolderClick: (Long) -> Unit,
    onNavigateToEmptyScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(start = 10.dp, end = 10.dp, top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = LocalContext.current.getGreeting(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                fontFamily = K2DFontFamily,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
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
            Button(
                onClick = { onNewNoteCreateClick(null) },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.create_note)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 5.dp, alignment = Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.folders),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onBackground
            )

            when (notesState) {
                UiState.Loading -> {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(brush = shimmer())
                    )
                }
                is UiState.Success -> {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.Start)
                    ) {
                        items(
                            items = notesState.data.second,
                            key = { it.id }
                        ) { folder ->
                            FolderCard(
                                folder = folder,
                                onFolderClick = onFolderClick
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 5.dp, alignment = Alignment.Top)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.recent_notes),
                textAlign = TextAlign.Start,
                fontFamily = K2DFontFamily,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onBackground
            )

            when (notesState) {
                UiState.Loading -> {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(brush = shimmer())
                    )
                }
                is UiState.Success -> {

                    val notes = notesState.data.first

                    if (notes.isEmpty()) {
                        onNavigateToEmptyScreen()
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            items(
                                items = notes,
                                key = { it.note.id }
                            ) {
                                NoteRow(
                                    modifier = Modifier.animateItem(),
                                    note = it.note,
                                    folderName = it.folder.name,
                                    photo = it.photo,
                                    onNoteClick = onNoteClick,
                                    onNoteDelete = onNoteDelete
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}