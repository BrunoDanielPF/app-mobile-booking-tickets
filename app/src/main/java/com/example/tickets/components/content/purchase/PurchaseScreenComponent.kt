package com.example.tickets.components.content.purchase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.R
import com.example.tickets.components.navigation.Routes

@Preview
@Composable
private fun ScreenDEtailsEventPurchasePreview() {
    ScreenDetailsEventPurchase(
        rememberNavController(),
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDetailsEventPurchase(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item { IconArrowBack(navController) }
        item {
            EventHeader(
                eventTitle = "Evento de Exemplo",
                location = "Localização",
                date = "Data",
                price = "$200"
            )
        }
        item { IconsEvent() }
        item { DescriptionEvent() }
        item { ButtonBuyNowEvent { navController.navigate(Routes.PAYMENT_SCREEN) } }
    }
}

@Composable
fun ButtonBuyNowEvent(onBuyNowClick: () -> Unit) {
    Button(
        onClick = onBuyNowClick,
        modifier = Modifier
            .padding(16.dp) // Add padding
            .fillMaxWidth() // Make button fill width
            .height(48.dp), // Set button height
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Blue
        )
    ) {
        Text(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            text = "Comprar agora"
        )
    }
}

@Composable
fun DescriptionEvent() {
    Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
        Text(
            text = "Descrição do evento",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Descrição detalhada do evento",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun IconsEvent(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp),
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Event owner profile picture"
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(
                text = "Nome do organizador",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "cargo ou funcao",
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            androidx.compose.material3.IconButton(
                onClick = { /*TODO: Handle message action*/ },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    contentDescription = "Message the organizer",
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_textsms)
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between buttons

            androidx.compose.material3.IconButton(
                onClick = { /*TODO: Handle call action*/ },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    contentDescription = "Call the organizer",
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_call_smartphone)
                )
            }
        }
    }
}

@Composable
fun EventHeader(eventTitle: String, location: String, date: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
        ) {
            Text(
                text = eventTitle,
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = location, style = MaterialTheme.typography.bodyMedium)
                Text(text = date, style = MaterialTheme.typography.bodyMedium)
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Text(
            text = price,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
        )
    }
}

@Composable
fun IconArrowBack(navController: NavController, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { navController.popBackStack() },
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Use vector drawable if possible
            contentDescription = "Back",
            modifier = Modifier.size(24.dp) // Adjust size for better visual
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Use vector drawable if possible
            contentDescription = "Event owner profile picture",
            modifier = Modifier
                .size(50.dp) // Set the size of your icon
                .border(
                    width = 2.dp, // Set the border width
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black, // Conditional border color
                    shape = CircleShape // Make the border rounded (circle)
                )
                .padding(4.dp) // Optional: Add padding inside the border
        )
    }
}