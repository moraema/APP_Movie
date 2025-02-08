package com.ema.my_project_crud.register.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ema.my_project_crud.register.data.model.UsuarioRequest
import com.ema.my_project_crud.register.data.repository.UsuarioAddRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val resgiterRepository = UsuarioAddRepository()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    fun addUsuario(usuarioRequest: UsuarioRequest) {
        try {
            viewModelScope.launch {
                val result =  resgiterRepository.addUsuario(usuarioRequest)
                _message.value = result.getOrElse { "Hubo un error al agregar la pelicula" }

                delay(2000)
                _message.value = null
            }
        } catch (e: Exception) {

        }
    }
}