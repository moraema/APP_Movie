package com.ema.my_project_crud.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieRequest(
    val titulo: String,
    val genero: String,
    val año: Int,
    val imagen: String
)

data class MovieResponse(
    @SerializedName("data")
    val movies: List<MovieRequest>
)