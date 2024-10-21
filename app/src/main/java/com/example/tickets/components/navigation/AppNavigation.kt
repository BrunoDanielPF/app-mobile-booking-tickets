package com.example.tickets.components.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
}

fun NavHostController.navigateTo(route: String) {
    this.navigate(route)
}

fun NavGraphBuilder.eventRoute(navController: NavController) {
    navigation(startDestination = "username", route = "login") {
        composable("username") {
            Text(
                text = "texto de texte"
            )
        }
    }
}