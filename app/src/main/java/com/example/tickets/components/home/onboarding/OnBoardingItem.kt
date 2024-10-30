package com.example.tickets.components.home.onboarding

import com.example.tickets.R
data class OnBoardingItem(
    val title: Int,
    val text: Int,
    val Image: Int,
) {
    companion object {

        fun get() = listOf(
            OnBoardingItem(R.string.app_name, R.string.app_name, R.drawable.event_image),
            OnBoardingItem(R.string.app_name, R.string.app_name, R.drawable.event_image),
            OnBoardingItem(R.string.app_name, R.string.app_name, R.drawable.event_image)
        )
    }
}