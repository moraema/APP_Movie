package com.ema.my_project_crud.register.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ema.my_project_crud.R
import com.ema.my_project_crud.register.data.model.UsuarioRequest

//@Preview(showBackground = true)
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navigationToHome: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    val message by registerViewModel.message.collectAsState()

    var isButtonEnabled = username.isNotBlank() && correo.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272729)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterHeader()

        Spacer(modifier = Modifier.height(20.dp))
        RegisterFields(
            username = username,
            onUsernameChange = { username = it },
            correo = correo,
            onCorreoChange = { correo = it },
            password = password,
            onPasswordChange = { password = it },
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            ispasswordVisible = isPasswordVisible,
            onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible },
            isConfirmPasswordVisible = isConfirmPasswordVisible,
            onIsConfirmPasswordVisibiltyChange = { isConfirmPasswordVisible = !isConfirmPasswordVisible}
        )

        Spacer(modifier = Modifier.height(20.dp))

        message?.let {
            Text(
                text = it,
                color = Color.Green,
                fontSize = 20.sp
            )
        }

        RegisterActions(isButtonEnabled, onAddUsuarioClick = {

            val usuarioRequest = UsuarioRequest(
                nombre = username,
                correo = correo,
                contraseña = password
            )

            registerViewModel.addUsuario(usuarioRequest)

            username = ""
            correo = ""
            password = ""
            confirmPassword = ""
        })

        Spacer(modifier = Modifier.height(20.dp))

        bottonAccion(onLoginClick = {navigationToHome()})
    }
}

@Composable
fun RegisterHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.agregar),
            contentDescription = "Login Image",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Crear cuenta",
            fontSize = 36.sp,
            color = Color(0xFFF25B98),
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun RegisterFields(
    username: String, onUsernameChange: (String) -> Unit,
    correo: String, onCorreoChange: (String) -> Unit,
    password: String, onPasswordChange: (String) -> Unit,
    confirmPassword: String, onConfirmPasswordChange: (String) -> Unit,
    ispasswordVisible: Boolean, onPasswordVisibilityChange: () -> Unit,
    isConfirmPasswordVisible: Boolean, onIsConfirmPasswordVisibiltyChange: () -> Unit
) {

    var passwordError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = { Text(text = "Username", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text("Emmanuel Lucas Morales", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Persona nueva", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged {  }
    )

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = correo,
        onValueChange = onCorreoChange,
        label = { Text(text = "E-mail", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text(text = "223729@ids.upchiapas.edu.mx", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "E-mail", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged {  }
    )

    Spacer(modifier = Modifier.height(20.dp))


    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = "Password", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text(text = "pasword", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "password", tint = White) },
        visualTransformation = if (ispasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            IconButton(
                onClick = onPasswordVisibilityChange
            ) {
                Icon(
                    imageVector = if (ispasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (ispasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = White
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged {  }
    )

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = {
            onConfirmPasswordChange(it)
            passwordError = it != password
        },
        label = { Text(text = "Confirm password", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text(text = "Confirm password", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm password", tint = White) },
        visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            IconButton(
                onClick = onIsConfirmPasswordVisibiltyChange
            ) {
                Icon(
                    imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isConfirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = White
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged {  }
    )

    if (passwordError) {
        Text(
            text = "Las contraseñas no coinciden",
            color = Color.Red,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 10.dp, top = 4.dp)
        )
    }

}

@Composable
fun RegisterActions(isButtonEnabled: Boolean,  onAddUsuarioClick: () -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { onAddUsuarioClick() },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4),
                disabledContainerColor = Color(0xFF8A70B0).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Crear cuenta",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
fun bottonAccion(onLoginClick:() -> Unit) {
    Text(
        text = "Iniciar Sesión",
        color = Color(0xFFF25B98),
        fontSize = 16.sp,
        modifier = Modifier
            .clickable { onLoginClick() }
    )
}

