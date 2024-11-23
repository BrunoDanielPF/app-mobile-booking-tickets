package com.example.tickets.services.data

import com.google.gson.annotations.SerializedName

data class ValidateAccountRequest(
    @SerializedName("email")
    val email: String,
    val code: Int
)
