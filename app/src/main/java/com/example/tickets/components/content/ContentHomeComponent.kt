package com.example.tickets.components.content


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn {
            item {
                CardsEvents()
            }
            item {
                CategoryListEvent()
            }
            item {
                EventCardList(navController)
            }
        }
    }
}