package me.theek.memox.feature.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.components.shimmer
import me.theek.memox.core.design_system.ui.theme.MemoXTheme
import me.theek.memox.core.model.Folder
import me.theek.memox.feature.note.R
import me.theek.memox.feature.note.UiState

@Composable
internal fun FoldersRow(
    selectedFolderId: Long?,
    isEmptySelectedFolder: Boolean,
    foldersState: UiState<List<Folder>>,
    onNewFolderCreateClick: () -> Unit,
    onFolderSelect: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = if(isEmptySelectedFolder) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.select_folder),
                maxLines = 1,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = if(isEmptySelectedFolder) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis
            )

            TextButton(onClick = onNewFolderCreateClick) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.create_new_folder)
                )
                Text(
                    text = stringResource(R.string.new_1),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        when (foldersState) {
            UiState.Empty -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.click_to_create_a_new_folder))
                }
            }
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
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(
                        items = foldersState.data,
                        key = { it.id }
                    ) {
                        FolderChip(
                            folder = it,
                            isSelected = selectedFolderId == it.id,
                            onFolderSelect = onFolderSelect
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FolderChip(
    isSelected: Boolean,
    folder: Folder,
    onFolderSelect: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        modifier = modifier.padding(horizontal = 5.dp),
        label = {
            Text(text = folder.name)
        },
        onClick = { onFolderSelect(folder.id) },
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.select_folder)
                )
            }
        } else null,
        selected = isSelected
    )
}

@Preview
@Composable
private fun FoldersRowPreview() {
    MemoXTheme {
        FoldersRow(
            selectedFolderId = 1L,
            isEmptySelectedFolder = false,
            foldersState = UiState.Empty,
            onNewFolderCreateClick = {},
            onFolderSelect = {}
        )
    }
}