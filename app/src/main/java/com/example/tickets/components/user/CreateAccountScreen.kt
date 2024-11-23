import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tickets.R
import com.example.tickets.components.samples.IconsApp
import com.example.tickets.services.data.UserPreferences
import com.example.tickets.services.performCreateAccount

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen(rememberNavController(), userPreferences = UserPreferences(rememberNavController().context))
}

@Composable
fun CreateAccountScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    userPreferences: UserPreferences
) {
    var email by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var nomeError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    fun validateInputs(): Boolean {
        var isValid = true

        if (email.isBlank()) {
            emailError = "E-mail é obrigatório"
            isValid = false
        } else {
            emailError = null
        }

        if (nome.isBlank()) {
            nomeError = "Nome completo é obrigatório"
            isValid = false
        } else {
            nomeError = null
        }

        if (password.isBlank()) {
            passwordError = "Senha é obrigatória"
            isValid = false
        } else if (!Regex("^(?=.*[A-Z])(?=.*[!@#\$&*])(?=.*\\d).{8,}\$").matches(password)) {
            passwordError = "Senha deve conter: 8 caracteres, uma letra maiúscula, um número e um caractere especial."
            isValid = false
        } else {
            passwordError = null
        }

        if (confirmPassword != password) {
            confirmPasswordError = "As senhas não correspondem"
            isValid = false
        } else {
            confirmPasswordError = null
        }

        return isValid
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        // Cabeçalho
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
                text = "Criar conta",
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
            // Campo de e-mail
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "E-mail") },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError != null,
                singleLine = true
            )
            if (emailError != null) {
                Text(
                    text = emailError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de nome completo
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome completo") },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Nome da pessoa") },
                modifier = Modifier.fillMaxWidth(),
                isError = nomeError != null,
                singleLine = true
            )
            if (nomeError != null) {
                Text(
                    text = nomeError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de senha
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
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
                isError = passwordError != null,
                singleLine = true
            )
            if (passwordError != null) {
                Text(
                    text = passwordError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de confirmação de senha
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar senha") },
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Confirmar senha") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                isError = confirmPasswordError != null,
                singleLine = true
            )
            if (confirmPasswordError != null) {
                Text(
                    text = confirmPasswordError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Indicador de carregamento ou botão
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = colorResource(id = R.color.main_amarelo_ouro)
                )
            } else {
                Button(
                    onClick = {
                        if (validateInputs()) {
                            isLoading = true
                            // Simulação de uma chamada de rede
                            performCreateAccount(email, password, nome, navHostController, userPreferences) {
                                isLoading = false // Quando finalizar, desativa o loading
                            }
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
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Criar conta"
                    )
                }
            }
        }
    }
}
