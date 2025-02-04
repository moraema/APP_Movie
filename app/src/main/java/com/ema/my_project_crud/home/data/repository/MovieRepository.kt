package com.ema.my_project_crud.home.data.repository

import android.util.Log
import com.ema.my_project_crud.core.network.RetrofitHelper
import com.ema.my_project_crud.home.data.model.MovieRequest



class MovieRepository() {

    private val movieService = RetrofitHelper.homeService

    suspend fun getMovies(): Result<List<MovieRequest>> {
        return try {

            val response = movieService.getMovies()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("Debug", "Respuesta de Api: $result")
                    Result.success(result.movies)
                } ?: Result.failure(Exception("Error: Respuesta vacía"))
            } else {
                val errorMessage = response.errorBody()?.toString() ?: "Hubo un error"
                Log.e("MovieRepository", "Error en la respuesta: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Excepción al obtener películas", e)
            Result.failure(e)
        }
    }
}