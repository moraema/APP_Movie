package com.ema.my_project_crud.addMovie.data.repository

import android.util.Log
import com.ema.my_project_crud.addMovie.data.model.MovieAddRequest
import com.ema.my_project_crud.core.network.RetrofitHelper
import com.ema.my_project_crud.addMovie.data.model.MovieAddResponse
import com.ema.my_project_crud.home.data.model.MovieRequest
import retrofit2.Response
import kotlin.Result

class MovieAddRepository() {
    private val addMovieService = RetrofitHelper.addMovieService

    suspend fun addMovie(movieAddRequest: MovieAddRequest): Result<String> {
        return try {
            val response: Response<MovieAddResponse> = addMovieService.addMovie(movieAddRequest)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("MovieRepository", "Respuesta: ${result.message}")
                    Result.success(result.message)
                } ?: Result.failure(Exception("Error: Respuesta vacía"))
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