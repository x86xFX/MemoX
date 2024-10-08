package me.theek.memox.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.icons.Folder_open
import me.theek.memox.core.model.Folder
import me.theek.memox.feature.home.R

@Composable
internal fun FolderCard(
    folder: Folder,
    onFolderClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(100.dp)
            .width(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onFolderClick(folder.id) }
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Folder_open,
            contentDescription = stringResource(id = R.string.folders),
            tint = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            modifier = Modifier.fillMaxSize(),
            text = folder.name,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            maxLines = 3
        )
    }
}