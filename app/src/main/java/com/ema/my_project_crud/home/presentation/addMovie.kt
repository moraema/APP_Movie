package com.ema.my_project_crud.home.presentation

import android.content.Intent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.ema.my_project_crud.home.data.model.MovieRequest
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.IconButton
import java.util.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ema.my_project_crud.R


//@Preview(showBackground = true)
@Composable
fun AddMovieScreen(viewModel: AddMovieViewModel, onNavigateHome: () -> Unit, onRecordMovie:() -> Unit) {

    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var año by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    val message by viewModel.message.collectAsState()

    var isButtonEnabled = titulo.isNotBlank() && genero.isNotBlank() && año.isNotBlank() && imagen.isNotBlank()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272729)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AddMovieHeader()

        Spacer(modifier = Modifier.height(20.dp))

        AddMovieFields(
            titulo = titulo,
            onTituloChange = { titulo = it},
            genero = genero,
            onGeneroChange = { genero = it},
            año = año,
            onAñoChange = { año = it},
            imagen = imagen,
            onImagenChange = { imagen = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        message?.let {
            Text(
                text = it,
                color = Color.Green,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        
        AddMovieActions(isButtonEnabled, onAddMovieClick = {

                    val movieRequest = MovieRequest(
                        titulo = titulo,
                        genero = genero,
                        año = año.toInt(),
                        imagen = imagen
                    )
                    viewModel.addMovie(movieRequest)

                    titulo = ""
                    genero = ""
                    año = ""
                    imagen = ""

        })

        Spacer(modifier = Modifier.height(20.dp))

        SeeMoviesActions(onAddMovieClick = { onNavigateHome() })

        Spacer(modifier = Modifier.height(20.dp))

        SpeechMovies(onRecordMovieClick = { onRecordMovie() })

    }
}

@Composable
fun AddMovieHeader() {
   Column(horizontalAlignment = Alignment.CenterHorizontally) {
       Image(
           painter = painterResource(id = R.drawable.pelicula),
           contentDescription = "Icon agregar pelicula",
           modifier = Modifier
               .size(150.dp)
       )

       Spacer(modifier = Modifier.height(20.dp))

       Text(
           text = "Agregar película",
           fontSize = 36.sp,
           color = Color(0xFFF25B98),
           fontWeight = FontWeight.Black,
       )
   }
}


@Composable
fun AddMovieFields(
    titulo: String, onTituloChange: (String) -> Unit,
    genero: String, onGeneroChange: (String) -> Unit,
    año: String, onAñoChange: (String) -> Unit,
    imagen: String, onImagenChange: (String) -> Unit
) {

    OutlinedTextField(
        value = titulo,
        onValueChange = onTituloChange,
        label = { Text(text = "titulo", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text("Loki", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Movie, contentDescription = "Película", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged { }
    )


    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = genero,
        onValueChange = onGeneroChange,
        label = { Text(text = "Género", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text("Acción", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Category, contentDescription = "Género", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged { }
    )


    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = año,
        onValueChange = onAñoChange,
        label = { Text(text = "Año", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text("2023", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Año", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged { }
    )

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = imagen,
        onValueChange = onImagenChange,
        label = { Text(text = "URL de Imagen", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text("URL de imagen", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Link, contentDescription = "Imagen URL", tint = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged { }
    )
}

@Composable
fun AddMovieActions(isButtonEnabled: Boolean, onAddMovieClick: () -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = { onAddMovieClick() },
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4),
                disabledContainerColor = Color(0xFF8A70B0).copy(alpha = 0.5f),
                contentColor = White,
                disabledContentColor = Color(0xFFBDBDBD)
            ),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(
                text = "Agregar película",
                color = White,
                fontWeight = FontWeight.Bold)
        }


    }
}

@Composable
fun SeeMoviesActions(onAddMovieClick: () -> Unit) {
    Text(
        text = "Ver las películas",
        color = Color(0xFFF25B98),
        fontSize = 16.sp,
        modifier = Modifier
            .height(32.dp)
            .clickable { onAddMovieClick() }
    )
}

@Composable
fun SpeechMovies(onRecordMovieClick: () -> Unit) {
    Text(
        text = "Recomendar peliculas",
        color = Color(0xFFF25B98),
        fontSize = 16.sp,
        modifier = Modifier
            .height(32.dp)
            .clickable { onRecordMovieClick() }
    )
}