package com.example.moviessearch.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


private const val TOKEN = "6ARZEMC-HEEMEBH-HG8CX3M-AHMCJYC"


interface MoviesApi {
    @GET("movie")
    fun getMovies(
        @Query("search")  search: String,
        @Query("field")  field: String = "year",
        @Query("token")  token: String = TOKEN,
        @Query("limit")  limit: String = "60",
    ): Call<MoviesList>
}