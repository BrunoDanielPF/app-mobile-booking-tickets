package com.example.tickets.services

import okhttp3.Credentials
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiTicketService {
    @GET("generate-qr-code")
    suspend fun generateQrCode(
        @Query("userName") userName: String,
        @Query("eventName") eventName: String,
        @Query("enabled") enabled: Boolean,
        @Query("id") id: Int
    ): Response<ResponseBody>

    @Headers("Content-Type: application/json", "Accept: */*")
    @POST("users/auth/signin")
    suspend fun signIn(@Body credentials: Credentials): Response<Unit>
}