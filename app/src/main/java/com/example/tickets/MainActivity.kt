package com.example.tickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.bottom.BottomNavigationBar
import com.example.tickets.components.content.MainScreenContentComponent
import com.example.tickets.components.samples.AnimatedVisibilityCookbook
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
        modifier = androidx.compose.ui.Modifier.padding(
            innerPadding
        )
    ) {
        composable(BottomNavItem.Home.screen_route) {
            MainScreenContentComponent()
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
        R.drawable.ic_home, "home")
    object Calendar : BottomNavItem("Calendário", R.drawable.ic_calendar, "calendar")
    object Maps : BottomNavItem("Mapa", R.drawable.ic_maps, "maps")
    object Profile : BottomNavItem("Perfil", R.drawable.ic_profile, "profile")
}