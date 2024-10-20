package com.example.tickets.components.content.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickets.R

@Preview
@Composable
private fun CardsEventsPreview() {
    CardsEvents()
}

@Composable
fun CardsEvents() {
    Column {
        Spacer(
            modifier = Modifier.padding(top = 6.dp)
        )
        // Campo de pesquisa
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "Pesquisar",
                    tint = Color.White
                )
            },
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Pesquisar...", color = Color.White) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.White,
                focusedPlaceholderColor = Color.White,
                unfocusedPlaceholderColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp) // Adiciona padding externo
                .background(Color(0xFF8f98ab), CircleShape),
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            shape = CircleShape
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.heightIn(max = 300.dp)
        ) {
            items(3) {
                Card(
                    elevation = CardDefaults.cardElevation(16.dp),
                    modifier = Modifier
                        .size(height = 260.dp, width = 200.dp)
                        .padding(16.dp),
                    shape = CardDefaults.outlinedShape
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.event_image),
                            contentDescription = "evento imagem",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .clip(shape = CardDefaults.outlinedShape)
                        )
                        Text(
                            text = "Batidao do funk, putaria liberada aaaaaa",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(horizontal = 10.dp)
                        )
                        Row {
                            DateDescriptionEvent(
                                Modifier
                                    .padding(horizontal = 10.dp)
                                    .size(10.dp)
                            )
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(8.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            Text(
                                text = "Saber mais",
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateDescriptionEvent(modifier: Modifier = Modifier) {
    Row() {
        DescriptionWithIcon(
            painterResource(id = R.drawable.ic_calendar),
            description = "20/09/2023",
            modifier = modifier
        )
        DescriptionWithIcon(
            painterResource(id = R.drawable.ic_maps),
            description = "Sao miguel",
            modifier = modifier
        )
    }
}

@Composable
fun DescriptionWithIcon(icon: Painter, description: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = description,
            modifier = modifier
        )
        Text(
            text = description,
            maxLines = 1,
            fontSize = 8.sp
        )
    }

}