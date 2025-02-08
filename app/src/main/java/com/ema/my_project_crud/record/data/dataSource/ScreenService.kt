package com.ema.my_project_crud.record.data.dataSource

import com.ema.my_project_crud.record.data.model.ScreenMovieResponse
import com.ema.my_project_crud.record.data.model.ScreenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ScreenService {

    @POST("recomendacion")
    suspend fun addScreenMovie(@Body screen: ScreenRequest) : Response<ScreenMovieResponse>
}