package com.ema.my_project_crud.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.ema.my_project_crud.R
import com.ema.my_project_crud.home.data.model.MovieRequest




//@Preview(showBackground = true)
@Composable
fun HomeMoviesScreen(homeViewModel: HomeViewModel, onAddMovie: () -> Unit, onLogout: () -> Unit) {

    val isLoading by homeViewModel.isLoading.observeAsState(false)
    val movies by homeViewModel.movies.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        homeViewModel.getMoviesScreens()

    }

    Log.d("HomeMoviesScreen", "Películas obtenidas Home: ${movies}")



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272729))
    ) {
        Column {
            HomeHeader(onLogoutClick = {onLogout()})


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(2.dp)
                    .background(Color.Magenta)
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (isLoading) {
                Text("Cargando pelicula .....",
                    color = Color.White
                )
            } else {
                if (movies.isNotEmpty()) {
                    MovieGrid(movies = movies)
                } else {
                    Text("No hay películas disponibles", color = Color.White)
                }
            }


        }

        AddMovieActions(onAddMovieClick =  {onAddMovie()})
    }
}

@Composable
fun MovieCard(movie: MovieRequest) {

    Column(modifier = Modifier.padding(16.dp)) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
           Image(
                painter = rememberAsyncImagePainter(model = movie.imagen),
                contentDescription = movie.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Log.d("MovieCard", "Mostrando película: ${movie.titulo}, imagen: ${movie.imagen}")

        }


        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = movie.titulo,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = movie.año.toString(),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 14.sp,
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = movie.genero,
                color = Color.Magenta,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun HomeHeader(onLogoutClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Películas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Magenta,
            modifier = Modifier.padding(top = 40.dp, bottom = 25.dp, start = 15.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { onLogoutClick() },
            modifier = Modifier
                .size(55.dp)
                .padding(top = 20.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cerrarsesion2),
                contentDescription = "Imagen cerrar sesión"
            )
        }
    }
}

@Composable
fun MovieGrid(movies: List<MovieRequest>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .zIndex(0f)
    ) {
        items(movies) { movie ->
            MovieCard(movie)
            Log.d("MovieGrid", "Renderizando ${movies.size} películas")

        }
    }
}

@Composable
fun AddMovieActions(onAddMovieClick: () -> Unit) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        IconButton(
            onClick = { onAddMovieClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = -10.dp, y = -10.dp)
                .size(100.dp)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.agregarmas),
                contentDescription = "Imagen agregar más"
            )
        }
    }
}
