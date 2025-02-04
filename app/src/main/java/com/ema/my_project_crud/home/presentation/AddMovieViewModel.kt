package com.ema.my_project_crud.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ema.my_project_crud.home.data.model.MovieRequest
import com.ema.my_project_crud.home.data.repository.MovieAddRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddMovieViewModel:ViewModel() {
    private val movieRepository = MovieAddRepository()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    fun addMovie(movieRequest: MovieRequest) {
        try {
            viewModelScope.launch {
                val result = movieRepository.addMovie(movieRequest)
                _message.value = result.getOrElse { "Hubo un error al agregar la pelicula" }

                delay(2000)
                _message.value = null
            }
        } catch (e: Exception) {

        }
    }


}