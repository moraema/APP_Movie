package com.ema.my_project_crud.home.data.dataSource


import com.ema.my_project_crud.home.data.model.MovieAddResponse
import com.ema.my_project_crud.home.data.model.MovieRequest
import com.ema.my_project_crud.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeService {

    @GET("peliculas")
    suspend fun getMovies(): Response<MovieResponse>

    @POST("peliculas")
    suspend fun addMovie(@Body movie: MovieRequest): Response<MovieAddResponse>
}