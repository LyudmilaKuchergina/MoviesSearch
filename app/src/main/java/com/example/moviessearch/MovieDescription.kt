package com.example.moviessearch

import com.google.gson.annotations.SerializedName

data class MovieDescription (
    @SerializedName( "ID")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

)
