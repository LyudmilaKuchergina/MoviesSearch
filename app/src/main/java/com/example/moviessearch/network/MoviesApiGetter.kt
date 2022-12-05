package com.example.moviessearch.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiGetter {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val moviesApi by lazy {  retrofit.create(MoviesApi::class.java) }
}