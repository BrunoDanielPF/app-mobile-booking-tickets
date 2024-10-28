package com.example.tickets.components.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tickets.components.home.content.purchase.PaymentScreen
import com.example.tickets.components.home.content.purchase.ScreenDetailsEventPurchase
import com.example.tickets.components.home.content.purchase.method.MethodPaymentScreen
import com.example.tickets.components.home.content.purchase.method.payment.SlugMethodPaymentScreen

fun NavHostController.navigateTo(route: String) {
    this.navigate(route)
}

fun NavGraphBuilder.eventRoute(navController: NavController) {
    navigation(startDestination = "username", route = "login") {
        composable(Routes.DETAILS_SCREEN) {
            ScreenDetailsEventPurchase(navController = navController, modifier = Modifier.fillMaxWidth())
        }
        composable(Routes.PAYMENT_SCREEN) {
            PaymentScreen(navController = navController)
        }
        composable(Routes.METHOD_PAYMENT_SCREEN) {
            MethodPaymentScreen(navController = navController)
        }
        composable(Routes.SLUG_METHOD_PAYMENT_SCREEN) {
            SlugMethodPaymentScreen(navController = navController)
        }
    }
}