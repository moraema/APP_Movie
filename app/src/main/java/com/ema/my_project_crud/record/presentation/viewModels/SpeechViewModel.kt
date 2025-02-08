package com.ema.my_project_crud.record.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ema.my_project_crud.record.data.model.ScreenRequest
import com.ema.my_project_crud.record.data.repository.ScreenAddRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpeechViewModel: ViewModel() {
    private val screenRepository = ScreenAddRepository()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    fun addScreen(screenRequest: ScreenRequest) {
        try {
            viewModelScope.launch {
                val result = screenRepository.screenAdd(screenRequest)
                _message.value = result.getOrElse { "Hubo un error al agregar la recomendacion" }

                delay(2000)
                _message.value = null
            }
        } catch (e: Exception) {

        }
    }
}