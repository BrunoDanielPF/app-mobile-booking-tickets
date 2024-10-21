package com.example.tickets.components.samples

import androidx.navigation.NavHostController

/**
 * Class to handle navigation. It should be injected into the screens' ViewModel
 */
class Navigator {

    private var navController: NavHostController? = null

    fun setController(controller: NavHostController) {
        navController = controller
    }

    fun clear() {
        navController = null
    }

    fun navigate(route: String) {
        navController?.navigate(route)
    }
}