package com.example.tickets.components.home.content


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.home.content.cards.CardsEvents
import com.example.tickets.components.home.content.cards.EventCardList
import com.example.tickets.components.home.content.events.CategoryListEvent

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