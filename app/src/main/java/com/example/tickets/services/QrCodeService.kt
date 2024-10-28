package com.example.tickets.services

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QrCodeService {
    @GET("generate-qr-code")
    suspend fun generateQrCode(
        @Query("userName") userName: String,
        @Query("eventName") eventName: String,
        @Query("enabled") enabled: Boolean,
        @Query("id") id: Int
    ): Response<ResponseBody>
}