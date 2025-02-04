package com.ema.my_project_crud.login.presentation

import android.opengl.Visibility
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ema.my_project_crud.R
import kotlinx.coroutines.delay


//@Preview(showBackground = true)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, onRegisterClick: () -> Unit, onLoginSuccess: () -> Unit){

    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val loginMessage by loginViewModel.loginMessage.collectAsState()
    var errorMessage by remember { mutableStateOf("") }

    val handler = remember { Handler(Looper.getMainLooper()) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272729)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginImage()
        Spacer(modifier = Modifier.height(20.dp))
        TextWelcome()

        Spacer(modifier = Modifier.height(20.dp))

        LoginFields(
            correo = correo,
            onCorreoChange = { correo = it},
            contraseña = contraseña,
            onContraseñaChange = { contraseña = it},
            ispasswordVisible = isPasswordVisible,
            onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible }
        )

        Spacer(modifier = Modifier.height(5.dp))

        ForgotPassword()

        Spacer(modifier = Modifier.height(25.dp))

        LoginButton(onLoginSuccessClick = {
            if (correo.isBlank() || contraseña.isBlank()) {
                errorMessage = "Debe llenar todos los campos para iniciar sesión"
                handler.postDelayed({ errorMessage = "" }, 1000)
            } else {
                loginViewModel.loginView(correo, contraseña)
            }
        })

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        if (!loginMessage.isNullOrEmpty()) {
            if (loginMessage == "Correo electrónico o contraseña incorrectos") {
                Text(text = loginMessage!!, color = Color.Red)
            } else {

                onLoginSuccess()
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        RegisterActions(onRegisterClick = { onRegisterClick() })

        Spacer(modifier = Modifier.height(25.dp))

        SocialLogin()
    }
}



@Composable
fun LoginImage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logopeli),
            contentDescription = "Login Image",
            modifier = Modifier.size(200.dp)
        )
    }
}


@Composable
fun TextWelcome() {
    Text(
        text = "Bienvenido",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = White
    )

    Spacer(modifier = Modifier.height(15.dp))

    Text(
        text = "Inicie sesión en su cuenta",
        color = White
    )
}

@Composable
fun LoginFields(
    correo: String,
    onCorreoChange: (String) -> Unit,
    contraseña: String,
    onContraseñaChange: (String) -> Unit,
    ispasswordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    OutlinedTextField(
        value = correo,
        onValueChange = onCorreoChange,
        label =  { Text(text = "Correo", color = White)},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = {Text("example@gmail.com", color = Color.LightGray)},
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "E-mail", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged { }
    )

    Spacer(modifier = Modifier.height(4.dp))

    OutlinedTextField(
        value = contraseña,
        onValueChange = onContraseñaChange,
        label =  { Text(text = "Password", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(15.dp),
        placeholder = {Text("password", color = Color.LightGray)},
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon", tint = White) },
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
    )
}

@Composable
fun ForgotPassword() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = "¿Olvidó su contraseña?",
            fontSize = 12.sp,
            color = Color(0xFFF25B98),
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable { }
        )
    }
}

@Composable
fun LoginButton( onLoginSuccessClick: () -> Unit) {
    Button(
        onClick = {  onLoginSuccessClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Iniciar Sesión",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black
        )
    }
}


@Composable
fun RegisterActions(onRegisterClick: () -> Unit) {
    Text(
        text = "No tiene una cuenta ¿Crear cuenta?",
        fontSize = 16.sp,
        color = White,
        modifier = Modifier
            .height(32.dp)
            .clickable { onRegisterClick() }
    )
}

@Composable
fun SocialLogin() {
    Text(
        text = "O iniciar sesión con:",
        fontSize = 16.sp,
        color = White
    )

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SocialMediaIcon(R.drawable.google)
        SocialMediaIcon(R.drawable.facebook)
        SocialMediaIcon(R.drawable.instagram)
    }
}

@Composable
fun SocialMediaIcon(resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "Social Media Icon",
        modifier = Modifier
            .size(60.dp)
            .clickable { }
    )
}
