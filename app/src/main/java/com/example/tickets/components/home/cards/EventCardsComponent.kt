package com.example.tickets.components.home.content.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.R
import com.example.tickets.components.colors.ColorsDefault
import com.example.tickets.components.navigation.Routes
import com.example.tickets.components.navigation.navigateTo

@Preview
@Composable
private fun EventCardsListPreview() {
    CardEvent(rememberNavController())
}

@Composable
fun EventCardList(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CardEvent(navController)
        CardEvent(navController)
        CardEvent(navController)

    }
}

@Composable
fun CardEvent(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // Align items vertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp)) // Add horizontal spacing

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Nome do evento",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp)) // Reduced spacing
                Text(
                    text = "20 de outubro, 23",
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Add horizontal spacing

            VerticalDivider(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .background(Color.Black)
            )
            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "R$ 100,00",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.main_amarelo_ouro),
                    fontSize = 12.sp
                )
                TextButton(
                    modifier = Modifier.wrapContentWidth(),
                    onClick = {
                        navController.navigateTo(Routes.DETAILS_SCREEN)
                    }
                ) {
                    Text(
                        text = "Comprar \n agora",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        style = TextStyle(brush = ColorsDefault.blackGradient()),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}