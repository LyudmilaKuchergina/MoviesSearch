package com.example.moviessearch.network

import com.example.moviessearch.MoviesJson
import com.google.gson.annotations.SerializedName

data class MoviesList (
    @SerializedName( "docs")
    val movies: List<MoviesJson>
)