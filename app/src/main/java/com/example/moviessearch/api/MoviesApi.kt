package com.example.moviessearch.api

import com.example.moviessearch.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val TOKEN = "6ARZEMC-HEEMEBH-HG8CX3M-AHMCJYC"


interface MoviesApi {
    @GET("movie")
    fun getMovies(
        @Query("search")  search: String,
        @Query("field")  field: String = "year"  ,
        @Query("token")  token: String = TOKEN,
    ): Call<MoviesList>
}