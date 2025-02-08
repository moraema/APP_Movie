package com.ema.my_project_crud.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ema.my_project_crud.home.presentation.AddMovieScreen
import com.ema.my_project_crud.home.presentation.AddMovieViewModel
import com.ema.my_project_crud.home.presentation.HomeMoviesScreen
import com.ema.my_project_crud.home.presentation.HomeViewModel
import com.ema.my_project_crud.login.presentation.LoginScreen
import com.ema.my_project_crud.login.presentation.LoginViewModel
import com.ema.my_project_crud.record.presentation.view.RecordMovieScreen
import com.ema.my_project_crud.record.presentation.viewModels.SpeechViewModel
import com.ema.my_project_crud.register.presentation.RegisterScreen
import com.ema.my_project_crud.register.presentation.RegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> { LoginScreen(LoginViewModel(),
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
        composable<AddMovie> { AddMovieScreen(AddMovieViewModel(),
            onNavigateHome = {navController.navigate(Home)},
            onRecordMovie = {navController.navigate(MovieSpeech)}
            )
        }

        composable<MovieSpeech> {
            RecordMovieScreen(SpeechViewModel())  {navController.navigate(Home)}
        }
    }
}