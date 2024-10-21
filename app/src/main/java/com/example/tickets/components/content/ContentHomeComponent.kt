package com.example.tickets.components.content


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.content.cards.CardsEvents
import com.example.tickets.components.content.cards.EventCardList
import com.example.tickets.components.content.events.CategoryListEvent

@Preview
@Composable
private fun MainScreenContentComponentPreview() {
    MainScreenContentComponent(rememberNavController())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreenContentComponent(navController: NavHostController) {
    val swipeableState = rememberSwipeableState(initialValue = 1)
    val anchors = mapOf(0f to 0, 1f to 1) // 0: Recolhido, 1: Expandido
    var isDragging by remember { mutableStateOf(false) }

    // Observe as mudanças no swipeableState para atualizar isDragging
    LaunchedEffect(swipeableState.currentValue) {
        isDragging = swipeableState.isAnimationRunning
    }
    val backgroundColor by animateColorAsState(
        targetValue = if (isDragging) Color.LightGray else Color.White, // Ajuste as cores conforme necessário
        animationSpec = tween(durationMillis = 200)
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Orientation.Vertical
                )
        ) {
            AnimatedVisibility(visible = swipeableState.currentValue == 1) {
                Column {
                    CardsEvents()
                    CategoryListEvent()
                }
            }
            EventCardList(navController)
        }
    }
}