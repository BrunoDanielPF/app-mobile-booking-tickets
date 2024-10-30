package com.example.tickets.components.home.content.purchase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.components.navigation.Routes
import com.example.tickets.R
import com.example.tickets.components.colors.ColorsDefault

@Preview
@Composable
private fun PaymentScreenPreview() {
    PaymentScreen(rememberNavController())
}

@Composable
fun PaymentScreen(navController: NavController, modifier: Modifier = Modifier) {

    var selectedButtonId by remember { mutableStateOf<Int?>(null) }

    var quantitySeats by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
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
                    text = "Ticket"
                )
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Lista",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* Ação de voltar */ }
                )
            }
            Row {
                Text(
                    text = "Selecione o tipo de ticket",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        item {
            SelectableButtons(buttons, selectedButtonId) {
                selectedButtonId = it
            }
        }
        item {
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Quantidade de lugares"
                )
                InputFieldSeats(quantitySeats) { newQuantity ->
                    quantitySeats = newQuantity
                }
            }
        }
        item {
            ValueTicketDescription(quantitySeats)
        }
        item {
            Column(
                modifier = Modifier
                    .height(150.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.METHOD_PAYMENT_SCREEN)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.main_amarelo_ouro)),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Continuar",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ValueTicketDescription(quantitySeats: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = SpaceBetween,
        ) {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Valor do Ticket"
            )
            Text(
                fontSize = 16.sp,
                text = "R$ $100"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "$quantitySeats x R$ 100,00"
            )
        }
        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Total"
            )
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "R$ ${quantitySeats * 100}"
            )
        }
    }
}

@Composable
fun InputFieldSeats(quantitySeats: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray, shape = CircleShape)
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Diminuir valor",
            modifier = Modifier
                .size(32.dp)
                .padding(8.dp)
                .clickable { onQuantityChange(if (quantitySeats > 0) quantitySeats - 1 else 0) }
        )

        BasicTextField(
            value = quantitySeats.toString(),
            onValueChange = { newValue ->
                onQuantityChange(newValue.toIntOrNull() ?: 0)
            },
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            ),
            singleLine = true,
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Aumentar valor",
            modifier = Modifier
                .size(32.dp)
                .padding(8.dp)
                .clickable { onQuantityChange(quantitySeats + 1) }
        )
    }
}

@Composable
fun SelectableButtons(
    buttons: List<ButtonData>,
    selectedButtonId: Int?,
    onButtonClick: (Int) -> Unit
) {
    // Linha para organizar os botões horizontalmente
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        buttons.forEach { button ->
            Button(
                onClick = { onButtonClick(button.id) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedButtonId == button.id) colorResource(id = R.color.main_amarelo_ouro) else Color.LightGray
                ),
                modifier = Modifier
                    .widthIn(min = 80.dp)
                    .size(width = 150.dp, height = 40.dp),
            ) {
                Text(
                    text = button.text,
                    color = if (selectedButtonId == button.id) Color.White else Color.Black
                )
            }
        }
    }
}

data class ButtonData(val id: Int, val text: String)

val buttons = listOf(
    ButtonData(1, "VIP"),
    ButtonData(2, "Economico"),
)
