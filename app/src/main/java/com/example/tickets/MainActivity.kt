package com.example.tickets

import CreateAccountScreen
import LoginPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.bottom.BottomNavigationBar
import com.example.tickets.components.home.content.MainScreenContentComponent
import com.example.tickets.components.home.content.purchase.PaymentScreen
import com.example.tickets.components.home.content.purchase.ScreenDetailsEventPurchase
import com.example.tickets.components.home.content.purchase.method.MethodPaymentScreen
import com.example.tickets.components.home.content.purchase.method.payment.SlugMethodPaymentScreen
import com.example.tickets.components.navigation.Routes
import com.example.tickets.components.navigation.eventRoute
import com.example.tickets.components.topbar.TopBar
import com.example.tickets.components.user.RecoverPasswordScreen
import com.example.tickets.ui.theme.TicketsTheme

class MainActivity : ComponentActivity() {
    private var isUserLoggedIn = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketsTheme {
                val navController = rememberNavController()
                MainScreen(
                    navHostController = navController,
                    isUserLoggedIn = isUserLoggedIn.value,
                    onLoginSuccess = { isUserLoggedIn.value = true }
                )
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen(rememberNavController(), false, { })
}

@Composable
fun MainScreen(
    navHostController: NavHostController,
    isUserLoggedIn: Boolean,
    onLoginSuccess: () -> Unit
) {
    Scaffold(
        topBar = {
            if (isUserLoggedIn) {
                TopBar(userName = "{Nome do usuário}", onFilterClick = { /* Ação de filtro */ })
            }
        },
        bottomBar = { BottomNavigationBar(navHostController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navHostController,
            innerPadding = innerPadding,
            onLoginSuccess = onLoginSuccess
        )
    }
}

@Composable
fun NavigationGraph(
    onLoginSuccess: () -> Unit,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController,
        startDestination = Routes.LOGIN_SCREEN,
        modifier = Modifier.padding(
            innerPadding
        )
    ) {
        eventRoute(navController)
        composable(Routes.LOGIN_SCREEN) {
            LoginPage(
                onLoginClick = { email, password ->
                    // Implemente a lógica de login aqui e redirecione para a tela principal se tiver sucesso
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onGoogleLoginClick = {
                    // Implemente a lógica do login com Google
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.RECOVER_PASSWORD_SCREEN)
                },
                onCreateAccountClick = {
                    navController.navigate(Routes.CREATE_ACCOUNT_SCREEN)
                },
                onLoginSuccess = {
                    onLoginSuccess()
                }
            )
        }
        composable(Routes.RECOVER_PASSWORD_SCREEN) {
            RecoverPasswordScreen()
        }
        composable(Routes.CREATE_ACCOUNT_SCREEN) {
            CreateAccountScreen()
        }
        composable(BottomNavItem.Home.screen_route) {
            MainScreenContentComponent(navController)
        }
        composable(BottomNavItem.Calendar.screen_route) {
            Text(text = "pagina de calendario")
            //CalendarScreen()
        }
        composable(BottomNavItem.Maps.screen_route) {
            Text(text = "pagina de maps")
            //MapsScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            Text(text = "pagina de perfil")
            //ProfileScreen()
        }
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

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem(
        "Início",
        R.drawable.ic_home, Routes.HOME
    )

    object Calendar : BottomNavItem(
        "Calendário",
        R.drawable.ic_calendar, Routes.CALENDAR
    )

    object Maps : BottomNavItem(
        "Mapa",
        R.drawable.ic_maps, Routes.MAPS
    )

    object Profile : BottomNavItem(
        "Perfil",
        R.drawable.ic_profile, Routes.PROFILE
    )
}