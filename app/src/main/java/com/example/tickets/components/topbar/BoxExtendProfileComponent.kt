package com.example.tickets.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.tickets.R

@Composable
fun BoxExtendProfileColor() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.cinza_perfil))
            .padding(bottom = 200.dp)
    ) {
    }
}