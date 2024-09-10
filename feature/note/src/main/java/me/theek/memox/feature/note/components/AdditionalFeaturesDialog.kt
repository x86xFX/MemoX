package me.theek.memox.feature.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.icons.Explore
import me.theek.memox.core.design_system.icons.Photo_library
import me.theek.memox.core.design_system.ui.theme.K2DFontFamily
import me.theek.memox.feature.note.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AdditionalFeaturesDialog(
    onDismissRequest: () -> Unit,
    onAddPhotosClick: () -> Unit,
    onAddLocationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        content = {
            Column (
                modifier = Modifier
                    .clip(AlertDialogDefaults.shape)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 15.dp),
                    text = "Available Features",
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontFamily = K2DFontFamily,
                    color = MaterialTheme.colorScheme.onSurface
                )

                AdditionalFeaturesDialogItem(
                    title = "Photos",
                    icon = Photo_library,
                    contentDescription = stringResource(R.string.location_feature),
                    onFeatureClick = onAddPhotosClick
                )

                AdditionalFeaturesDialogItem(
                    title = "Location",
                    icon = Explore,
                    contentDescription = stringResource(R.string.location_feature),
                    onFeatureClick = onAddLocationClick
                )

                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(R.string.close))
                }
            }
        }
    )
}

@Composable
private fun AdditionalFeaturesDialogItem(
    title: String,
    icon: ImageVector,
    contentDescription: String?,
    onFeatureClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onFeatureClick)
            .padding(5.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            maxLines = 1,
            softWrap = true,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
                .padding(5.dp),
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}