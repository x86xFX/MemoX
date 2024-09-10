package me.theek.memox.core.design_system.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Smartphone_camera: ImageVector
	get() {
		if (Smartphone_camera1 != null) {
			return Smartphone_camera1!!
		}
		Smartphone_camera1 = ImageVector.Builder(
            name = "Smartphone_camera",
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
				moveTo(280f, 920f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(200f, 840f)
				verticalLineToRelative(-720f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(280f, 40f)
				horizontalLineToRelative(400f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(760f, 120f)
				verticalLineToRelative(120f)
				horizontalLineTo(280f)
				verticalLineToRelative(480f)
				horizontalLineToRelative(480f)
				verticalLineToRelative(120f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(680f, 920f)
				close()
				moveToRelative(0f, -80f)
				horizontalLineToRelative(400f)
				verticalLineToRelative(-40f)
				horizontalLineTo(280f)
				close()
				moveToRelative(0f, -680f)
				horizontalLineToRelative(400f)
				verticalLineToRelative(-40f)
				horizontalLineTo(280f)
				close()
				moveToRelative(0f, 0f)
				verticalLineToRelative(-40f)
				close()
				moveToRelative(0f, 680f)
				verticalLineToRelative(-40f)
				close()
				moveToRelative(300f, -200f)
				quadToRelative(-25f, 0f, -42.5f, -17.5f)
				reflectiveQuadTo(520f, 580f)
				verticalLineToRelative(-160f)
				quadToRelative(0f, -25f, 17.5f, -42.5f)
				reflectiveQuadTo(580f, 360f)
				horizontalLineToRelative(40f)
				lineToRelative(40f, -40f)
				horizontalLineToRelative(80f)
				lineToRelative(40f, 40f)
				horizontalLineToRelative(40f)
				quadToRelative(25f, 0f, 42.5f, 17.5f)
				reflectiveQuadTo(880f, 420f)
				verticalLineToRelative(160f)
				quadToRelative(0f, 25f, -17.5f, 42.5f)
				reflectiveQuadTo(820f, 640f)
				close()
				moveToRelative(120f, -70f)
				quadToRelative(29f, 0f, 49.5f, -20.5f)
				reflectiveQuadTo(770f, 500f)
				reflectiveQuadToRelative(-20.5f, -49.5f)
				reflectiveQuadTo(700f, 430f)
				reflectiveQuadToRelative(-49.5f, 20.5f)
				reflectiveQuadTo(630f, 500f)
				reflectiveQuadToRelative(20.5f, 49.5f)
				reflectiveQuadTo(700f, 570f)
			}
		}.build()
		return Smartphone_camera1!!
	}

private var Smartphone_camera1: ImageVector? = null
