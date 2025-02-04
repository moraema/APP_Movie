package com.ema.my_project_crud.register.data.dataSource

import com.ema.my_project_crud.register.data.model.UsuarioAddResponse
import com.ema.my_project_crud.register.data.model.UsuarioRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("usuarios")
    suspend fun addUsuario(@Body usuario: UsuarioRequest): Response<UsuarioAddResponse>;
}