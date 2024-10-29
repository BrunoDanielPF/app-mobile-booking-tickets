package com.example.tickets.components.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.example.tickets.R

object ColorsDefault {

    @Composable
    fun blackGradient(): Brush {
        return Brush.horizontalGradient(
            colors = listOf(
                colorResource(id = R.color.buttom_linear_black), // Cor inicial do gradiente
                colorResource(id = R.color.buttom_linear_black_second_color)  // Cor final do gradiente
            )
        )
    }
}