package me.theek.memox.core.design_system.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Photo_library: ImageVector
	get() {
		if (Photo_library1 != null) {
			return Photo_library1!!
		}
		Photo_library1 = ImageVector.Builder(
            name = "Photo_library",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
			path(
    			fill = SolidColor(Color.Black),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(360f, 560f)
				horizontalLineToRelative(400f)
				lineTo(622f, 380f)
				lineToRelative(-92f, 120f)
				lineToRelative(-62f, -80f)
				close()
				moveToRelative(-40f, 160f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(240f, 640f)
				verticalLineToRelative(-480f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(320f, 80f)
				horizontalLineToRelative(480f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(880f, 160f)
				verticalLineToRelative(480f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(800f, 720f)
				close()
				moveToRelative(0f, -80f)
				horizontalLineToRelative(480f)
				verticalLineToRelative(-480f)
				horizontalLineTo(320f)
				close()
				moveTo(160f, 880f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(80f, 800f)
				verticalLineToRelative(-560f)
				horizontalLineToRelative(80f)
				verticalLineToRelative(560f)
				horizontalLineToRelative(560f)
				verticalLineToRelative(80f)
				close()
				moveToRelative(160f, -720f)
				verticalLineToRelative(480f)
				close()
			}
		}.build()
		return Photo_library1!!
	}

private var Photo_library1: ImageVector? = null
