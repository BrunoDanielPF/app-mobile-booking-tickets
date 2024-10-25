package com.example.tickets.components.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.content.purchase.PaymentScreen
import com.example.tickets.components.content.purchase.ScreenDetailsEventPurchase

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
}

fun NavHostController.navigateTo(route: String) {
    this.navigate(route)
}

fun NavGraphBuilder.eventRoute(navController: NavController) {
    navigation(startDestination = "username", route = "login") {
        composable(Routes.DETAILS_SCREEN) {
            ScreenDetailsEventPurchase(navController = navController, modifier = Modifier.fillMaxWidth())
        }
        composable(Routes.PAYMENT_SCREEN) {
            PaymentScreen()
        }
    }
}