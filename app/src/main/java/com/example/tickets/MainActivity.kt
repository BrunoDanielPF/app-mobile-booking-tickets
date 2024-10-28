package com.example.tickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.bottom.BottomNavigationBar
import com.example.tickets.components.home.content.MainScreenContentComponent
import com.example.tickets.components.navigation.Routes
import com.example.tickets.components.navigation.eventRoute
import com.example.tickets.components.topbar.TopBar
import com.example.tickets.ui.theme.TIcketsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TIcketsTheme {
               MainScreen()
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar(userName = "{Nome do usuário}", onFilterClick = { /* TODO */ }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, innerPadding = innerPadding)
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: androidx.compose.foundation.layout.PaddingValues
) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Home.screen_route,
        modifier = Modifier.padding(
            innerPadding
        )
    ) {
        eventRoute(navController)
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
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Início",
        R.drawable.ic_home, Routes.HOME)

    object Calendar : BottomNavItem("Calendário",
        R.drawable.ic_calendar, Routes.CALENDAR)

    object Maps : BottomNavItem("Mapa",
        R.drawable.ic_maps, Routes.MAPS)

    object Profile : BottomNavItem("Perfil",
        R.drawable.ic_profile, Routes.PROFILE)
}