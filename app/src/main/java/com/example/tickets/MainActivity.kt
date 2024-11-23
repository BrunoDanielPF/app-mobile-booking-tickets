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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.tickets.components.user.ConfirmAccountScreen
import com.example.tickets.components.user.RecoverPasswordScreen
import com.example.tickets.services.createApiService
import com.example.tickets.services.data.UserPreferences
import com.example.tickets.services.performCreateAccount
import com.example.tickets.ui.theme.TicketsTheme
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var isUserLoggedIn = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketsTheme {
                val navController = rememberNavController()
                val userPreferences = UserPreferences(navController.context)
                MainScreen(
                    navHostController = navController,
                    isUserLoggedIn = isUserLoggedIn.value,
                    onLoginSuccess = { isUserLoggedIn.value = true },
                    userPreferences
                )
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen(
        rememberNavController(),
        false,
        { },
        UserPreferences(rememberNavController().context)
    )
}

@Composable
fun MainScreen(
    navHostController: NavHostController,
    isUserLoggedIn: Boolean,
    onLoginSuccess: () -> Unit,
    userPreferences: UserPreferences
) {
    var userName by remember { mutableStateOf("") }
    val apiService = remember { createApiService() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            if (isUserLoggedIn) {
                LaunchedEffect(Unit) {

                    val email = userPreferences.userEmail.firstOrNull() ?: ""
                    if (email.isNotBlank()) {

                        coroutineScope.launch {
                            try {
                                val response = apiService.getUser(email)
                                if (response.isSuccessful) {
                                    val user = response.body()
                                    userName = user?.nome ?: "Usuário"
                                } else {
                                    println(
                                        "Erro ao buscar o usuário: ${
                                            response.errorBody()?.string()
                                        }"
                                    )
                                }
                            } catch (e: Exception) {
                                println("Erro ao fazer a chamada de rede: ${e.message}")
                            }
                        }
                    }
                }
                TopBar(userName = userName, onFilterClick = { /* Ação de filtro */ })
            }
        },
        bottomBar = { BottomNavigationBar(navHostController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navHostController,
            innerPadding = innerPadding,
            onLoginSuccess = onLoginSuccess,
            userPreferences = userPreferences
        )
    }
}

@Composable
fun NavigationGraph(
    onLoginSuccess: () -> Unit,
    navController: NavHostController,
    innerPadding: PaddingValues,
    userPreferences: UserPreferences
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
                },
                navController,
                userPreferences
            )
        }
        composable(Routes.RECOVER_PASSWORD_SCREEN) {
            RecoverPasswordScreen()
        }
        composable(Routes.CREATE_ACCOUNT_SCREEN) {
            CreateAccountScreen(navHostController = navController, userPreferences = userPreferences)
        }
        composable(Routes.CONFIRM_EMAIL_SCREEN) {
            var email by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                email = userPreferences.userEmail.firstOrNull()
            }
            ConfirmAccountScreen(email = email, navHostController = navController)
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
            ScreenDetailsEventPurchase(
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )
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