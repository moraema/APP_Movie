package com.ema.my_project_crud.register.data.model

import com.google.gson.annotations.SerializedName

data class UsuarioRequest(
    val nombre: String,
    val correo: String,
    val contraseña: String
)

