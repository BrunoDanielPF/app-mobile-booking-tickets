package com.example.tickets.components.samples

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


object IconsApp {
     val Visibility_off: ImageVector
        get() {
            if (_Visibility_off != null) {
                return _Visibility_off!!
            }
            _Visibility_off = ImageVector.Builder(
                name = "Visibility_off",
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
                    moveTo(644f, 532f)
                    lineToRelative(-58f, -58f)
                    quadToRelative(9f, -47f, -27f, -88f)
                    reflectiveQuadToRelative(-93f, -32f)
                    lineToRelative(-58f, -58f)
                    quadToRelative(17f, -8f, 34.5f, -12f)
                    reflectiveQuadToRelative(37.5f, -4f)
                    quadToRelative(75f, 0f, 127.5f, 52.5f)
                    reflectiveQuadTo(660f, 460f)
                    quadToRelative(0f, 20f, -4f, 37.5f)
                    reflectiveQuadTo(644f, 532f)
                    moveToRelative(128f, 126f)
                    lineToRelative(-58f, -56f)
                    quadToRelative(38f, -29f, 67.5f, -63.5f)
                    reflectiveQuadTo(832f, 460f)
                    quadToRelative(-50f, -101f, -143.5f, -160.5f)
                    reflectiveQuadTo(480f, 240f)
                    quadToRelative(-29f, 0f, -57f, 4f)
                    reflectiveQuadToRelative(-55f, 12f)
                    lineToRelative(-62f, -62f)
                    quadToRelative(41f, -17f, 84f, -25.5f)
                    reflectiveQuadToRelative(90f, -8.5f)
                    quadToRelative(151f, 0f, 269f, 83.5f)
                    reflectiveQuadTo(920f, 460f)
                    quadToRelative(-23f, 59f, -60.5f, 109.5f)
                    reflectiveQuadTo(772f, 658f)
                    moveToRelative(20f, 246f)
                    lineTo(624f, 738f)
                    quadToRelative(-35f, 11f, -70.5f, 16.5f)
                    reflectiveQuadTo(480f, 760f)
                    quadToRelative(-151f, 0f, -269f, -83.5f)
                    reflectiveQuadTo(40f, 460f)
                    quadToRelative(21f, -53f, 53f, -98.5f)
                    reflectiveQuadToRelative(73f, -81.5f)
                    lineTo(56f, 168f)
                    lineToRelative(56f, -56f)
                    lineToRelative(736f, 736f)
                    close()
                    moveTo(222f, 336f)
                    quadToRelative(-29f, 26f, -53f, 57f)
                    reflectiveQuadToRelative(-41f, 67f)
                    quadToRelative(50f, 101f, 143.5f, 160.5f)
                    reflectiveQuadTo(480f, 680f)
                    quadToRelative(20f, 0f, 39f, -2.5f)
                    reflectiveQuadToRelative(39f, -5.5f)
                    lineToRelative(-36f, -38f)
                    quadToRelative(-11f, 3f, -21f, 4.5f)
                    reflectiveQuadToRelative(-21f, 1.5f)
                    quadToRelative(-75f, 0f, -127.5f, -52.5f)
                    reflectiveQuadTo(300f, 460f)
                    quadToRelative(0f, -11f, 1.5f, -21f)
                    reflectiveQuadToRelative(4.5f, -21f)
                    close()
                    moveToRelative(168f, 168f)
                }
            }.build()
            return _Visibility_off!!
        }

    private var _Visibility_off: ImageVector? = null

    public val Visibility: ImageVector
        get() {
            if (_Visibility != null) {
                return _Visibility!!
            }
            _Visibility = ImageVector.Builder(
                name = "Visibility",
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
                    moveTo(480f, 640f)
                    quadToRelative(75f, 0f, 127.5f, -52.5f)
                    reflectiveQuadTo(660f, 460f)
                    reflectiveQuadToRelative(-52.5f, -127.5f)
                    reflectiveQuadTo(480f, 280f)
                    reflectiveQuadToRelative(-127.5f, 52.5f)
                    reflectiveQuadTo(300f, 460f)
                    reflectiveQuadToRelative(52.5f, 127.5f)
                    reflectiveQuadTo(480f, 640f)
                    moveToRelative(0f, -72f)
                    quadToRelative(-45f, 0f, -76.5f, -31.5f)
                    reflectiveQuadTo(372f, 460f)
                    reflectiveQuadToRelative(31.5f, -76.5f)
                    reflectiveQuadTo(480f, 352f)
                    reflectiveQuadToRelative(76.5f, 31.5f)
                    reflectiveQuadTo(588f, 460f)
                    reflectiveQuadToRelative(-31.5f, 76.5f)
                    reflectiveQuadTo(480f, 568f)
                    moveToRelative(0f, 192f)
                    quadToRelative(-146f, 0f, -266f, -81.5f)
                    reflectiveQuadTo(40f, 460f)
                    quadToRelative(54f, -137f, 174f, -218.5f)
                    reflectiveQuadTo(480f, 160f)
                    reflectiveQuadToRelative(266f, 81.5f)
                    reflectiveQuadTo(920f, 460f)
                    quadToRelative(-54f, 137f, -174f, 218.5f)
                    reflectiveQuadTo(480f, 760f)
                    moveToRelative(0f, -80f)
                    quadToRelative(113f, 0f, 207.5f, -59.5f)
                    reflectiveQuadTo(832f, 460f)
                    quadToRelative(-50f, -101f, -144.5f, -160.5f)
                    reflectiveQuadTo(480f, 240f)
                    reflectiveQuadToRelative(-207.5f, 59.5f)
                    reflectiveQuadTo(128f, 460f)
                    quadToRelative(50f, 101f, 144.5f, 160.5f)
                    reflectiveQuadTo(480f, 680f)
                }
            }.build()
            return _Visibility!!
        }

    private var _Visibility: ImageVector? = null

    public val Google: ImageVector
        get() {
            if (_Google != null) {
                return _Google!!
            }
            _Google = ImageVector.Builder(
                name = "Google",
                defaultWidth = 16.dp,
                defaultHeight = 16.dp,
                viewportWidth = 16f,
                viewportHeight = 16f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(15.545f, 6.558f)
                    arcToRelative(9.4f, 9.4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.139f, 1.626f)
                    curveToRelative(0f, 2.434f, -0.87f, 4.492f, -2.384f, 5.885f)
                    horizontalLineToRelative(0.002f)
                    curveTo(11.978f, 15.292f, 10.158f, 16f, 8f, 16f)
                    arcTo(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, 8f, 0f)
                    arcToRelative(7.7f, 7.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.352f, 2.082f)
                    lineToRelative(-2.284f, 2.284f)
                    arcTo(4.35f, 4.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, 8f, 3.166f)
                    curveToRelative(-2.087f, 0f, -3.86f, 1.408f, -4.492f, 3.304f)
                    arcToRelative(4.8f, 4.8f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 3.063f)
                    horizontalLineToRelative(0.003f)
                    curveToRelative(0.635f, 1.893f, 2.405f, 3.301f, 4.492f, 3.301f)
                    curveToRelative(1.078f, 0f, 2.004f, -0.276f, 2.722f, -0.764f)
                    horizontalLineToRelative(-0.003f)
                    arcToRelative(3.7f, 3.7f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.599f, -2.431f)
                    horizontalLineTo(8f)
                    verticalLineToRelative(-3.08f)
                    close()
                }
            }.build()
            return _Google!!
        }

    private var _Google: ImageVector? = null

}