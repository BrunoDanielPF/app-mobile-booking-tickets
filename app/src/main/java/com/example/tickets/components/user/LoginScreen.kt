import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickets.R
import com.example.tickets.components.samples.IconsApp
import com.example.tickets.components.validations.isPasswordValid

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPagePreview() {
    LoginPage({ _, _ -> }, { }, { }, { }, { })
}

@Composable
fun LoginPage(
    onLoginClick: (String, String) -> Unit,
    onGoogleLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var rememberAccount by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
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
                text = "Login",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { /* Ação de info */ }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "E-mail") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = password,
                onValueChange = {
                    password = it
                    isPasswordValid = isPasswordValid(password)
                },
                label = { Text("Senha") },
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Senha") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) IconsApp.Visibility_off else IconsApp.Visibility,
                            contentDescription = if (isPasswordVisible) "Esconder senha" else "Mostrar senha"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = !isPasswordValid,
            )

            if (!isPasswordValid) {
                Text(
                    text = "A senha precisa ter no mínimo uma letra maiúscula, um caractere especial e pelo menos 8 caracteres.",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberAccount,
                        onCheckedChange = { rememberAccount = it }
                    )
                    Text("Lembrar conta", color = Color.Gray, fontSize = 14.sp)
                }
                TextButton(onClick = onForgotPasswordClick) {
                    Text("Recuperar senha", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onLoginClick(email, password)
                    onLoginSuccess() // Define `isUserLoggedIn` como true após o login
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.main_amarelo_ouro),
                    contentColor = Color.White
                )
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Login"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Link "Criar conta" para novos usuários
            TextButton(onClick = onCreateAccountClick) {
                Text(
                    "Não tem uma conta? Criar conta",
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Text(
                    "Continuar com",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = onGoogleLoginClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = IconsApp.Google,
                    contentDescription = "Login com Google",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}