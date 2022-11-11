package com.example.moviessearch

import com.google.gson.annotations.SerializedName

data class MoviesList (
    @SerializedName( "docs")
    val movies: List<MoviesJson>
)