package com.example.tickets.services

import android.util.Base64
import androidx.navigation.NavHostController
import com.example.tickets.components.navigation.Routes
import com.example.tickets.services.data.CreateAccountRequest
import com.example.tickets.services.data.LoginRequest
import com.example.tickets.services.data.UserPreferences
import com.example.tickets.services.data.Usuario
import com.example.tickets.services.data.ValidateAccountRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    suspend fun login(@Body loginRequest: LoginRequest): Response<Unit>

    @Headers("Content-Type: application/json", "Accept: */*")
    @POST("users/auth/signup")
    suspend fun createAccount(@Body createAccountRequest: CreateAccountRequest): Response<Unit>

    @GET("users/auth")
    suspend fun getUser(@Query("email") email: String): Response<Usuario>

    @POST("/users/auth/validated")
    suspend fun validateEmailFromAccountEmail(@Body validateAccountRequest: ValidateAccountRequest): Response<Unit>
}

fun createApiService(): ApiTicketService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://app-tickets-api-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiTicketService::class.java)
}

fun createApiServiceWithToken(token: String): ApiTicketService {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build()
            chain.proceed(request)
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://app-tickets-api-production.up.railway.app/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiTicketService::class.java)
}

fun performLogin(
    userPreferences: UserPreferences,
    navController: NavHostController,
    email: String,
    password: String,
    onLoginSuccess: () -> Unit
) {
    if (email.isNotBlank() && password.isNotBlank()) {
        val apiService = createApiService()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val token = createBasicToken(email, password)
                    userPreferences.saveUser(email, token)


                    withContext(Dispatchers.Main) {
                        println("Login bem-sucedido com token $token")
                        onLoginSuccess()
                        navController.navigate(Routes.HOME)
                    }
                } else {
                    println("Falha no login: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Erro ao fazer login: ${e.message}")
            }
        }
    } else {
        println("Preencha todos os campos")
    }
}

fun performCreateAccount(
    email: String,
    password: String,
    userName: String,
    navController: NavHostController,
    userPreferences: UserPreferences,
    onCompletion: () -> Unit
) {
    if (email.isNotBlank() && password.isNotBlank() && userName.isNotBlank()) {
        val apiService = createApiService()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.createAccount(CreateAccountRequest(email, userName, password))
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        userPreferences.saveUser(email, createBasicToken(email, password))
                        navController.navigate(Routes.CONFIRM_EMAIL_SCREEN)
                    } else {
                        println("Falha na criação de conta: ${response.errorBody()?.string()}")
                    }
                    onCompletion() // Executa o callback no final, independentemente do resultado
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println("Falha na criação de conta: ${e.message}")
                    onCompletion() // Executa o callback em caso de exceção
                }
            }
        }
    } else {
        onCompletion()
    }
}

fun performValidateEmail(
    email: String,
    code: Int,
    navHostController: NavHostController,
    onCompletion: () -> Unit
) {
    val apiService = createApiService()

    if (email.isNotBlank()) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    apiService.validateEmailFromAccountEmail(ValidateAccountRequest(email, code))
                if (response.isSuccessful) {
                    withContext(
                        Dispatchers.Main
                    ) {
                        navHostController.navigate(Routes.HOME)
                        onCompletion()
                    }
                }
            } catch (e: Exception) {
                println("Falha na validação de email: ${e.message}")
                onCompletion()
            }
        }
    }
}


fun createBasicToken(email: String, password: String): String {
    val credentials = "$email:$password"
    return "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
}