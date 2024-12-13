package com.example.tickets.components.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation

fun NavHostController.navigateTo(route: String) {
    this.navigate(route)
}

fun NavGraphBuilder.eventRoute(navController: NavController) {
    navigation(startDestination = "username", route = "login") {

    }
}