package com.ema.my_project_crud.record.data.repository

import android.util.Log
import com.ema.my_project_crud.core.network.RetrofitHelper
import com.ema.my_project_crud.record.data.model.ScreenMovieResponse
import com.ema.my_project_crud.record.data.model.ScreenRequest
import retrofit2.Response

class ScreenAddRepository() {

    private val screenService = RetrofitHelper.speechService

    suspend fun screenAdd(screenRequest: ScreenRequest): Result<String> {
        return try {
            val response: Response<ScreenMovieResponse> = screenService.addScreenMovie(screenRequest)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("MovieRepository", "Respuesta: ${result.message}")
                    Result.success(result.message)
                } ?: Result.failure(Exception("Error: Respuesta vacia"))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Hubo un error"
                Log.e("MovieRepository", "Error en la respuesta: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Hubo un error al agrar la pelicua")
            Result.failure(e)
        }
    }
}