package com.example.tickets.components.home.content.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickets.R
import com.example.tickets.components.colors.ColorsDefault

@Preview(showBackground = true)
@Composable
private fun CategoryListComponent() {
    CategoryListEvent(
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
    )
}

@Composable
fun CategoryListEvent(modifier: Modifier = Modifier) {
    var selectedFilter by remember { mutableStateOf("Música") }

    Column(modifier = modifier) {
        TitleEventList()
        Category(selectedFilter = remember { mutableStateOf(selectedFilter) }, modifier = Modifier.padding(12.dp))
    }
}

@Composable
fun TitleEventList() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Escolha a categoria",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 15.dp)
                .wrapContentWidth(Alignment.Start)
        )
        Text(
            text = "Ver todos",
            color = colorResource(id = R.color.main_amarelo_ouro),
            modifier = Modifier
                .clickable(
                    onClick = { /*TODO*/ }
                )
                .padding(end = 12.dp)
                .wrapContentWidth(Alignment.End),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Category(selectedFilter: MutableState<String>, modifier: Modifier = Modifier) {
    val items = listOf(
        Pair("Música", R.drawable.ic_music),
        Pair("Esportes", R.drawable.ic_sports),
        Pair("Teatro", R.drawable.ic_theater),
        Pair("Cinema", R.drawable.ic_cinema)
    )

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            Button(
                onClick = { selectedFilter.value = item.first },
                modifier = Modifier
                    .background(
                        color = if (selectedFilter.value == item.first) {
                            colorResource(id = R.color.main_amarelo_ouro) // Orange when selected
                        } else {
                            Color.LightGray // Grayed out when not selected
                        },
                        shape = ButtonDefaults.shape
                    )
                    .width(120.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                enabled = selectedFilter.value != item.first
            ) {
                Icon(
                    painter = painterResource(id = item.second),
                    contentDescription = item.first,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = item.first,
                    fontSize = 12.sp,
                    maxLines = 1,
                    color = Color.White
                )
            }
        }
    }
}