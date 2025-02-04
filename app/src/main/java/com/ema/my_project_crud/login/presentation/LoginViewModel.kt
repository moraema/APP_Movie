package com.ema.my_project_crud.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ema.my_project_crud.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    private val _loginMessage = MutableStateFlow<String?>(null)
    val loginMessage: StateFlow<String?> = _loginMessage

    fun loginView(email: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(email, password)
            _loginMessage.value = result.getOrElse { error ->
                val errorMessage = error.message ?: ""
                if (errorMessage.contains("Correo electrónico o contraseña incorrectos")) {
                    "Correo electrónico o contraseña incorrectos"
                } else {
                    "Hubo un error. Inténtalo de nuevo"
                }
            }
        }
    }
}