package com.example.moviessearch

import com.google.gson.annotations.SerializedName

data class MoviesJson(

    @SerializedName( "id")
    val title_id: Int,

    @SerializedName("name")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("year")
    val year: String,

    @SerializedName("poster")
    val image: PosterJson?

)

data class PosterJson(
    @SerializedName( "url")
    val url: String?
)


