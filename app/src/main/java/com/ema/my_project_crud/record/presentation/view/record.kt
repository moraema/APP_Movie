package com.ema.my_project_crud.record.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Movie
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
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ema.my_project_crud.record.data.model.ScreenRequest
import com.ema.my_project_crud.record.domain.UseCase.SpeechUseCase
import com.ema.my_project_crud.record.presentation.utils.SpeechLaunher
import com.ema.my_project_crud.record.presentation.viewModels.SpeechViewModel

//@Preview(showBackground = true)
@Composable
fun RecordMovieScreen(recordViewModel: SpeechViewModel ,onNavigateBack: () -> Unit) {
    var movie by remember { mutableStateOf("") }
    var isButtonEnabled = movie.isNotBlank()
    val mesage  by recordViewModel.message.collectAsState()

    val speechLauncher = SpeechLaunher { result ->
        movie = result
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272729)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AddMovieHeader()

        Spacer(modifier = Modifier.height(20.dp))

        AddRecord(
            movie = movie,
            onMovieChange = { movie = it},
            onMicClick = { speechLauncher() }
        )

        Spacer(modifier = Modifier.height(20.dp))

        mesage?.let {
            Text(
                text = it,
                color = Color.Green,
                fontSize = 20.sp
            )
        }

        AddMovieRecord(isButtonEnabled, onAddMovieClick = {

           val screenRequest = ScreenRequest(
               recomendacion_titulo = movie
           )

            recordViewModel.addScreen(screenRequest)

            movie = ""

        })

        Spacer(modifier = Modifier.height(20.dp))

        SeeMoviesActions(onAddMovieClick = { onNavigateBack() })
    }
}


@Composable
fun AddMovieHeader() {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿Deseas una pelicula que no existe?",
            fontSize = 36.sp,
            color = Color(0xFFF25B98),
            fontWeight = FontWeight.Black,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Puedes dar tu recomendacion aqui, el equipo te notificara cuando la pelicula sea agregada",
            fontSize = 15.sp,
            color = Color(0xFFF25B98),
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun AddRecord(
    movie: String,
    onMovieChange: (String) -> Unit,
    onMicClick: () -> Unit
) {
    OutlinedTextField(
        value = movie,
        onValueChange = onMovieChange,
        label = { Text(text = "titulo", color = White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text(text = "Ejemplo: Loki", color = LightGray) },
        leadingIcon = { Icon(Icons.Default.Movie, contentDescription = "Titulo Pelicula", tint = White) },
        trailingIcon = {
            IconButton(onClick = {
                   onMicClick()
            }) {
                Icon(imageVector = Icons.Default.Mic, contentDescription = "Grabar", tint = White)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged {  }
    )
}

@Composable
fun AddMovieRecord(isButtonEnabled : Boolean, onAddMovieClick: () -> Unit, ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){

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
                text = "Agregar recomendacion",
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