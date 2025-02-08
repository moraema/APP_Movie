package com.ema.my_project_crud.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ema.my_project_crud.addMovie.presentation.view.AddMovieScreen
import com.ema.my_project_crud.addMovie.presentation.viewModel.AddMovieViewModel
import com.ema.my_project_crud.home.presentation.view.HomeMoviesScreen
import com.ema.my_project_crud.home.presentation.viewModel.HomeViewModel
import com.ema.my_project_crud.login.presentation.view.LoginScreen
import com.ema.my_project_crud.login.presentation.viewModel.LoginViewModel
import com.ema.my_project_crud.record.presentation.view.RecordMovieScreen
import com.ema.my_project_crud.record.presentation.viewModels.SpeechViewModel
import com.ema.my_project_crud.register.presentation.view.RegisterScreen
import com.ema.my_project_crud.register.presentation.viewModel.RegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> { LoginScreen(
            LoginViewModel(),
            onRegisterClick = { navController.navigate(Register)},
            onLoginSuccess = { navController.navigate(Home)}
            )
        }

        composable<Register> { RegisterScreen(RegisterViewModel()) { navController.navigate(Login)} }

        composable<Home> { HomeMoviesScreen(
            homeViewModel = homeViewModel,
            onAddMovie = { navController.navigate(AddMovie) },
            onLogout = { navController.navigate(Login) {
                   popUpTo(Login) { inclusive = true}
                 }
              }
            )
        }
        composable<AddMovie> { AddMovieScreen(
            AddMovieViewModel(),
            onNavigateHome = {navController.navigate(Home)},
            onRecordMovie = {navController.navigate(MovieSpeech)}
            )
        }

        composable<MovieSpeech> {
            RecordMovieScreen(SpeechViewModel())  {navController.navigate(Home)}
        }
    }
}