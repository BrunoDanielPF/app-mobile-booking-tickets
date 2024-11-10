package com.example.tickets.components.home.content.purchase.method.payment

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.services.ApiTicketService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


@Preview
@Composable
private fun SlugMethodPaymentScreenPreview() {
    SlugMethodPaymentScreen(navController = rememberNavController())
}

@Composable
fun SlugMethodPaymentScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Estado para controlar o QR Code e o estado de carregamento
    var qrCodeState by remember { mutableStateOf<QrCodeState>(QrCodeState(isLoading = true)) }

    LaunchedEffect(Unit) {
        qrCodeState = try {
            val qrCode = generateQrCode("JohnDoe", "Sample Event", true, 12345)
            QrCodeState(isLoading = false, qrCode = qrCode)
        } catch (e: Exception) {
            QrCodeState(isLoading = false, error = "Error generating QR Code: ${e.message}")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            qrCodeState.isLoading -> CircularProgressIndicator() // Indicador de carregamento
            qrCodeState.qrCode != null -> {
                // Exibir QR Code dentro de um Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Image(
                        bitmap = qrCodeState.qrCode!!,
                        contentDescription = "QR Code",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f) // Manter proporção do QR Code
                            .padding(16.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
//                        DownloadQrCodeImage(qrCodeState.qrCode!!)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Baixar imagem")
                }
            }

            else -> Text(
                qrCodeState.error ?: "Erro, consulte os administradores, $qrCodeState.error"
            )
        }
    }
}

@Composable
fun DownloadQrCodeImage(qrCode: ImageBitmap) {
    return
}

suspend fun generateQrCode(
    userName: String,
    eventName: String,
    enabled: Boolean,
    id: Int
): ImageBitmap? {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://app-tickets-api-production.up.railway.app/") // Your base URL
        .addConverterFactory(ScalarsConverterFactory.create()) // Add converter for String responses
        .build()

    val apiTicketService = retrofit.create(ApiTicketService::class.java)
    return try {
        val response = apiTicketService.generateQrCode(userName, eventName, enabled, id)
        if (response.isSuccessful) {
            val inputStream = response.body()?.byteStream()
            if (inputStream != null) {
                BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
            } else {
                null
            }
        } else {
            Log.e("QrCodeGenerator", "Error generating QR code: ${response.code()}")
            null
        }
    } catch (e: Exception) {
        Log.e("QrCodeGenerator", "Error generating QR code", e)
        null
    }
}

// Classe de estado do QR Code, contendo o bitmap e o status de carregamento
data class QrCodeState(
    val isLoading: Boolean = false,
    val qrCode: ImageBitmap? = null,
    val error: String? = null
)