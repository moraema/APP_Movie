package com.ema.my_project_crud.register.data.repository

import android.util.Log
import com.ema.my_project_crud.core.network.RetrofitHelper
import com.ema.my_project_crud.register.data.model.UsuarioAddResponse
import com.ema.my_project_crud.register.data.model.UsuarioRequest
import retrofit2.Response

class UsuarioAddRepository() {
    private val registerService = RetrofitHelper.registerService

    suspend fun addUsuario(usuarioRequest: UsuarioRequest): Result<String> {
        return try {
            val response: Response<UsuarioAddResponse> = registerService.addUsuario(usuarioRequest)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("RegisterAddRepository", "Respuesta: ${result.message}")
                    Result.success(result.message)
                } ?: Result.failure(Exception("Error: La respuesta fue vacia"))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Hubo un error"
                Log.e("RegisterAddRepository", "Error en la respuesta: ${errorMessage}")
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Log.e("UsuarioAddRepository", "Hubo un error al agregar al usuario")
            Result.failure(e)
        }
    }
}