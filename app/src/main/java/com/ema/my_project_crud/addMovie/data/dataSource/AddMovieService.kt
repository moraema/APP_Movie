package com.ema.my_project_crud.addMovie.data.dataSource


import com.ema.my_project_crud.addMovie.data.model.MovieAddRequest
import com.ema.my_project_crud.addMovie.data.model.MovieAddResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddMovieService {
    @POST("peliculas")
    suspend fun addMovie(@Body Addmovie: MovieAddRequest): Response<MovieAddResponse>
}