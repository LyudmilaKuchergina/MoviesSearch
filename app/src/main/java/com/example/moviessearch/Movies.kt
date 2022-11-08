package com.example.moviessearch

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName( "id")
    val title_id: Int,

    @SerializedName("name")
    val title: String,

    @SerializedName("poster")
    val image: Poster
)

data class Poster(
    @SerializedName( "url")
    val url: String
)


