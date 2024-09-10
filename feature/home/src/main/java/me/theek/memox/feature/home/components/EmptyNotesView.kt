package me.theek.memox.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.theek.memox.core.design_system.ui.theme.Onboarding_Background_Dark_Blue
import me.theek.memox.feature.home.R

@Composable
internal fun EmptyNotesView(
    onNoteCreateClicked: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    topStartShapeBrush: Brush = Brush.horizontalGradient(listOf(MaterialTheme.colorScheme.background, Onboarding_Background_Dark_Blue)),
    bottomEndShapeBrush: Brush = Brush.horizontalGradient(listOf(Onboarding_Background_Dark_Blue, MaterialTheme.colorScheme.background))
) {
    Column (
        modifier = modifier
            .background(backgroundColor)
            .drawWithCache {
                onDrawBehind {
                    val width = size.width
                    val height = size.height

                    val pathTopStart = Path().apply {
                        moveTo(
                            x = 0f,
                            y = 0f
                        )
                        lineTo(
                            x = width,
                            y = 0f
                        )
                        lineTo(
                            x = width,
                            y = height.times(0.04f)
                        )
                        lineTo(
                            x = width / 3.3f,
                            y = height.times(0.14f)
                        )
                        cubicTo(
                            x1 = width / 4f,
                            y1 = height.times(0.15f),
                            x2 = width / 5f,
                            y2 = height / 5.9f,
                            x3 = width / 7f,
                            y3 = height / 3.8f
                        )
                        lineTo(
                            x = 0f,
                            y = height / 2f
                        )
                        close()
                    }

                    val pathBottomEnd = Path().apply {
                        moveTo(
                            x = width,
                            y = height
                        )
                        lineTo(
                            x = 0f,
                            y = height
                        )
                        lineTo(
                            x = 0f,
                            y = height / 3f * 2.9f
                        )
                        lineTo(
                            x = width / 3f * 1.9f,
                            y = height / 3f * 2.75f
                        )
                        cubicTo(
                            x1 = width / 3f * 1.9f,
                            y1 = height / 3f * 2.75f,
                            x2 = width / 3f * 2.39f,
                            y2 = height / 1.1f,
                            x3 = width / 3f * 2.45f,
                            y3 = height / 3f * 2.6f
                        )
                        lineTo(
                            x = width,
                            y = height / 1.8f
                        )
                    }

                    drawPath(
                        path = pathTopStart,
                        brush = topStartShapeBrush
                    )

                    drawPath(
                        path = pathBottomEnd,
                        brush = bottomEndShapeBrush
                    )
                }
            },
        verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(fraction = 0.7f),
            text = stringResource(R.string.empty_notes_message),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground
        )

        IconButton(
            onClick = onNoteCreateClicked,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground)
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(R.string.create_note),
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}