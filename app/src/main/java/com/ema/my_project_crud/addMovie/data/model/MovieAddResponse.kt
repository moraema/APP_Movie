package com.ema.my_project_crud.addMovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieAddResponse(
    @SerializedName("message")
    val message: String
)
