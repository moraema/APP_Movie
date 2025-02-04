package com.ema.my_project_crud.login.data.dataSource

import com.ema.my_project_crud.login.data.model.LoginRequest
import com.ema.my_project_crud.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}