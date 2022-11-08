package com.example.moviessearch.api

import com.example.moviessearch.MovieDescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("/movie/{id}")
    fun getMovie(
        @Path("id") id: Int
    ): Call<MovieDescription>
}
