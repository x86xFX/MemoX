package me.theek.memox.feature.onboarding.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import me.theek.memox.core.design_system.ui.theme.MemoXTheme
import me.theek.memox.core.design_system.ui.theme.Onboarding_Background_Dark_Blue
import me.theek.memox.core.design_system.ui.theme.Onboarding_Background_Dark_Gray

@Composable
internal fun OnboardBackgroundDesign(
    shapeBrush: Brush = Brush.horizontalGradient(listOf(Onboarding_Background_Dark_Blue, Onboarding_Background_Dark_Gray)),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                onDrawBehind {
                    val width = size.width
                    val height = size.height

                    drawRect(color = backgroundColor)

                    val path = Path().apply {
                        moveTo(
                            x = 0f,
                            y = height / 8f
                        )
                        cubicTo(
                            x1 = 0f,
                            y1 = height / 6f,
                            x2 = width / 4f * 2f,
                            y2 = height / 2.5f,
                            x3 = width,
                            y3 = height / 4f
                        )
                        lineTo(
                            x = width,
                            y = height / 2f
                        )
                        moveTo(
                            x = width,
                            y = height / 2f
                        )
                        cubicTo(
                            x1 = width,
                            y1 = height / 2f,
                            x2 = width / 2f,
                            y2 = height / 2.5f,
                            x3 = 0f,
                            y3 = height / 5f * 3f
                        )
                        lineTo(
                            x = 0f,
                            y = height / 8f
                        )
                        close()
                    }
                    drawPath(
                        path = path,
                        brush = shapeBrush,
                        style = Fill
                    )
                }
            }
    ) {
        content()
    }
}

@Preview
@Composable
private fun BackgroundDesignPreview() {
    MemoXTheme {
        OnboardBackgroundDesign(content = {})
    }
}