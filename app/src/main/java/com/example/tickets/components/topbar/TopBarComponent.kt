package com.example.tickets.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickets.R

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(userName = "Usuário", onFilterClick = { /* TODO */ })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    userName: String,
    userImageUrl: String? = null, // URL da imagem do usuário (pode ser null)
    onFilterClick: () -> Unit // Callback para o clique no botão de filtro
) {
    // Define a imagem a ser exibida (foto do usuário ou padrão)
    val userImage = if (userImageUrl != null) {
        // TODO: Carregar a imagem do usuário a partir da URL (userImageUrl)
        // Por enquanto, usando uma imagem padrão
        painterResource(id = R.drawable.user_profile_image_placeholder)
    } else {
        painterResource(id = R.drawable.user_profile_image_placeholder)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.cinza_perfil))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 32.dp) //sobra para nao sobrepor os icones de notificacao dos aplicativos
        ) {
            Image(
                painter = userImage,
                contentDescription = "Foto do usuário",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    color = Color.White,
                    text = "Olá, $userName!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.weight(1f))

            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.filter_icon),
                    contentDescription = "Filtro",
                    tint = Color.White
                )
            }
        }
    }
}