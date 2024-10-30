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

    }
}