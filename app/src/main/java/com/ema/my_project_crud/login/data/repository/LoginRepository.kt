package com.ema.my_project_crud.login.data.repository

import com.ema.my_project_crud.core.network.RetrofitHelper
import com.ema.my_project_crud.login.data.model.LoginRequest
import com.ema.my_project_crud.login.data.model.LoginResponse
import retrofit2.Response
import android.util.Log

class LoginRepository {
    private val autService = RetrofitHelper.authService

    suspend fun login(correo: String, contraseña: String): Result<String> {
        return try {
            val response: Response<LoginResponse> = autService.login(LoginRequest(correo, contraseña))

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.success) {
                        Log.d("LoginRepository", "Inicio de sesión exitoso: ${it.message}")
                        Result.success(it.message)
                    } else {
                        Log.e("LoginRepository", "Inicio de sesión fallido: ${it.message}")
                        Result.failure(Exception(it.message))
                    }
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = errorBody ?: "Error desconocido"

                Log.e("LoginRepository", "Error en la respuesta: $errorMessage")
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Log.e("LoginRepository", "Error de conexión", e)
            Result.failure(e)
        }
    }
}