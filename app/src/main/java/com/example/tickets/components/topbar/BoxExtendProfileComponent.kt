package com.example.tickets.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tickets.components.colors.ColorsDefault.blackGradient


@Preview(showBackground = true)
@Composable
fun GraphicsBrushColorStopUsage() {
    // [START android_compose_brush_color_stop]
    val colorStops = arrayOf(
        0.0f to Color.Yellow,
        0.2f to Color.Red,
        1f to Color.Blue
    )
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.horizontalGradient(colorStops = colorStops))
    )
    // [END android_compose_brush_color_stop]
}

@Composable
@Preview(showBackground = true)
fun BoxExtendProfileColor() {
    Box(
        modifier = Modifier
            .background(brush = blackGradient())
            .fillMaxWidth()
            .padding(bottom = 200.dp)
    ) {
    }
}