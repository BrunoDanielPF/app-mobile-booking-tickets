package com.example.tickets.services.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("e-mail")
    val email: String,
    val senha: String
) {
}

data class CreateAccountRequest(
    @SerializedName("e-mail")
    val email: String,
    val nome: String,
    val senha: String
) {
}