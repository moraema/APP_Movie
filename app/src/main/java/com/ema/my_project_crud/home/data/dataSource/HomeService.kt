package com.ema.my_project_crud.home.data.dataSource


import com.ema.my_project_crud.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET


interface HomeService {
    @GET("peliculas")
    suspend fun getMovies(): Response<MovieResponse>

}