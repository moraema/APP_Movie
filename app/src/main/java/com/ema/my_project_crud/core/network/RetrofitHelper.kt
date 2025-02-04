package com.ema.my_project_crud.core.network

import com.ema.my_project_crud.home.data.dataSource.HomeService
import com.ema.my_project_crud.login.data.dataSource.LoginService
import com.ema.my_project_crud.register.data.dataSource.RegisterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
   private const val BASE_URL = "http://3.224.14.28:3001/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val homeService: HomeService by lazy {
        retrofit.create(HomeService::class.java)
    }

    val registerService: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }

    val authService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }
}