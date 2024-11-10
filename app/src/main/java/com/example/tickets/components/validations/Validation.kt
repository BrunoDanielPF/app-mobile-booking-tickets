package com.example.tickets.components.validations

fun isPasswordValid(password: String): Boolean {
    val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%\\^&\\*])[A-Za-z\\d!@#\$%\\^&\\*]{8,}$")
    return passwordRegex.matches(password)
}