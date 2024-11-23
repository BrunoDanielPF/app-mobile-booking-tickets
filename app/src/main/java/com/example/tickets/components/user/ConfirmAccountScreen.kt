package com.example.tickets.components.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.R
import com.example.tickets.services.performValidateEmail

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ConfirmAccountScreenPreview() {
    ConfirmAccountScreen(email = "frandalozzo1@gmail.com", navHostController = rememberNavController())
}

@Composable
fun ConfirmAccountScreen(email: String?, modifier: Modifier = Modifier, navHostController: NavHostController) {
    var code by remember { mutableStateOf("") }
    val focusRequesters = remember { List(4) { FocusRequester() } }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { /* Ação de voltar */ }
            )
            Text(
                text = "Verificação",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(
                modifier
            )
        }
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                buildAnnotatedString {
                    append("Um e-mail de verificação foi enviado para: \n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("$email")
                    }
                }
            )
        }

//JANELA DE CODIGO DO E-MAIL
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 0..3) {
                OutlinedTextField(
                    value = code.getOrNull(i)?.toString() ?: "",
                    onValueChange = { newDigit ->
                        if (newDigit.length <= 1) {
                            val updatedCode = buildString {
                                append(code.padEnd(4, ' ')) // Garante que `code` tenha pelo menos 4 caracteres
                                setCharAt(i, newDigit.firstOrNull() ?: ' ')
                            }.trim() // Remove espaços adicionais no final
                            code = updatedCode
                        }
                        if (newDigit.isNotEmpty() && i < 3) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .width(64.dp)
                        .padding(4.dp)
                        .focusRequester(focusRequesters[i]),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1
                )
            }
        }
        LaunchedEffect(Unit) {
            focusRequesters.firstOrNull()?.requestFocus()
        }
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = colorResource(id = R.color.main_amarelo_ouro)
                    )
                } else {
                    Button(
                        onClick = {
                            if (!email.isNullOrEmpty() && code.length == 4) {
                                isLoading = true
                                performValidateEmail(email, code.toInt(), navHostController) {
                                    isLoading = false // Quando finalizar, desativa o loading
                                }
                            } else {
                                // Mostre um erro apropriado
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.main_amarelo_ouro),
                            contentColor = Color.White
                        )
                    ) {
                        androidx.compose.material.Text(
                            fontWeight = FontWeight.Bold,
                            text = "Continuar"
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier.padding(12.dp)
            )
            CountdownTimer()
        }
    }
}

@Composable
fun CountdownTimer() {
    var remainingTime by remember { mutableStateOf(15 * 60) } // Tempo inicial em segundos (15 minutos)

    LaunchedEffect(remainingTime) {
        if (remainingTime > 0) {
            kotlinx.coroutines.delay(1000L) // Aguarda 1 segundo
            remainingTime-- // Decrementa o tempo restante
        } else {
            remainingTime = 15 * 60 // Reseta para 15 minutos
        }
    }

    val minutes = remainingTime / 60
    val seconds = remainingTime % 60

    Text(
        text = String.format("Código sendo reenviado em %02d:%02d", minutes, seconds),
        style = MaterialTheme.typography.labelMedium
    )
}