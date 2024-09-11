package me.theek.memox.core.design_system.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Folder_open: ImageVector
	get() {
		if (Folder_open1 != null) {
			return Folder_open1!!
		}
		Folder_open1 = ImageVector.Builder(
            name = "Folder_open",
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
				moveTo(160f, 800f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(80f, 720f)
				verticalLineToRelative(-480f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(160f, 160f)
				horizontalLineToRelative(240f)
				lineToRelative(80f, 80f)
				horizontalLineToRelative(320f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(880f, 320f)
				horizontalLineTo(447f)
				lineToRelative(-80f, -80f)
				horizontalLineTo(160f)
				verticalLineToRelative(480f)
				lineToRelative(96f, -320f)
				horizontalLineToRelative(684f)
				lineTo(837f, 743f)
				quadToRelative(-8f, 26f, -29.5f, 41.5f)
				reflectiveQuadTo(760f, 800f)
				close()
				moveToRelative(84f, -80f)
				horizontalLineToRelative(516f)
				lineToRelative(72f, -240f)
				horizontalLineTo(316f)
				close()
				moveToRelative(0f, 0f)
				lineToRelative(72f, -240f)
				close()
				moveToRelative(-84f, -400f)
				verticalLineToRelative(-80f)
				close()
			}
		}.build()
		return Folder_open1!!
	}

private var Folder_open1: ImageVector? = null
