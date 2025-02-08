package com.ema.my_project_crud.addMovie.data.model

data class MovieAddRequest(
    val titulo: String,
    val genero: String,
    val año: Int,
    val imagen: String
)
