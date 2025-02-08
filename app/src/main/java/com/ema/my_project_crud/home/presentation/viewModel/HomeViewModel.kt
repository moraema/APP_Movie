package com.ema.my_project_crud.home.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ema.my_project_crud.home.data.model.MovieRequest
import com.ema.my_project_crud.home.data.repository.MovieRepository

class HomeViewModel: ViewModel() {
    private val movieRepository = MovieRepository()

    private val _movies = MutableLiveData<List<MovieRequest>>()
    val movies: LiveData<List<MovieRequest>> get() = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun getMoviesScreens() {
        _isLoading.value = true
        try {
            val result = movieRepository.getMovies()
            if (result.isSuccess){
                val moviesList = result.getOrDefault(emptyList())
                Log.d("HomeViewModel", "Películas obtenidas: $moviesList")
                _movies.postValue(moviesList)

                _isLoading.value = false

            }
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Excepción al obtener películas: $e")
        }
    }
}