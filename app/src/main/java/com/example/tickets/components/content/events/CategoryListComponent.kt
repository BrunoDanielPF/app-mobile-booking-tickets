package com.example.tickets.components.content.events

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickets.R

@Preview
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
    Column(modifier = modifier) {
        TitleEventList()
        Category(modifier = Modifier.padding(12.dp))
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
            color = Color.Blue,
            modifier = Modifier
                .clickable(
                    onClick = { /*TODO*/ }
                )
                .padding(end = 12.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Composable
fun Category(modifier: Modifier = Modifier) {
    val items = listOf(
        Pair("Esportes", R.drawable.ic_sports), // Substitua pelos seus ícones
        Pair("Música", R.drawable.ic_music),
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
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = item.second),
                    contentDescription = item.first,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = item.first,
                    fontSize = 12.sp,
                    maxLines = 1
//                    textAlign = TextAlign.Center
                )
            }
        }
    }
}