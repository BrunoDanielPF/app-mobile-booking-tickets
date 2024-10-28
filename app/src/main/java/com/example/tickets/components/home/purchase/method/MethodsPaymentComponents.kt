package com.example.tickets.components.home.content.purchase.method

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.R
import com.example.tickets.components.navigation.Routes

@Preview
@Composable
private fun MethodPaymentScreenPreview() {
    MethodPaymentScreen(rememberNavController())
}

@Composable
fun MethodPaymentScreen(navController: NavController, modifier: Modifier = Modifier) {

    var selectedCardId by remember { mutableStateOf<Int?>(null) }
    val cards = listOf(
        Triple(R.drawable.ic_home, "Google pay", 1),
        Triple(R.drawable.ic_home, "Pix", 3)
    )
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.navigate("home")
                        }
                )
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    text = "Pagamento"
                )
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Lista",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* Ação de voltar */ }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Método de pagamento",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                TextButton(
                    onClick = { /* Ação do botão */ },
                ) {
                    Text(
                        text = "Adicionar novo",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 2,
                        color = colorResource(id = R.color.main_amarelo_ouro)
                    )
                }
            }
        }
        item {
                cards.forEach { (iconRes, cardName, cardId) ->
                    CardsPayment(
                        icon = {
                            Icon(
                                painterResource(id = iconRes),
                                contentDescription = cardName
                            )
                        },
                        cardName = cardName,
                        cardId = cardId,
                        selectedCardId = selectedCardId,
                        onCardSelected = { newSelectedCardId ->
                            selectedCardId = newSelectedCardId
                        }
                    )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .height(150.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.SLUG_METHOD_PAYMENT_SCREEN)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Continuar"
                    )
                }
            }
        }
    }
}


@Composable
fun CardsPayment(
    icon: @Composable () -> Unit,
    cardName: String,
    cardId: Int, // Add cardId
    selectedCardId: Int?,
    onCardSelected: (Int) -> Unit
) {
    val isChecked = cardId == selectedCardId

    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = cardName,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { onCardSelected(cardId) }
                )
            }
        }
    }
}