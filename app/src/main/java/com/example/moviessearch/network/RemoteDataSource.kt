package com.example.moviessearch.network

import android.util.Log
import com.example.moviessearch.Movies
import com.example.moviessearch.MoviesJson

class RemoteDataSource(moviesApiGetter: MoviesApiGetter) {

    private val moviesApi = moviesApiGetter.moviesApi

    private fun mapFromJson(movies: List<MoviesJson>): List<Movies> {
        return movies.mapIndexed { index, it ->
            Movies(
                id = index,
                url = it.image?.url.orEmpty(),
                title = it.title.orEmpty(),
                description = it.description.orEmpty(),
                year = it.year
            )
        }
    }

    fun loadMovies(
        year: String,
        onReady: (List<Movies>) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = moviesApi.getMovies(year).execute()
            val json = response.body()
            val moviesResponse = json?.movies
            Log.d("TAG", "movies(${moviesResponse?.size}) = $moviesResponse")
            moviesResponse?.run {
                val result = mapFromJson(this.filter {
                    it.title != null && it.image?.url != null && it.description != null
                })
                Log.d("TAG", "movies result size: ${result.size}")
                onReady(result)
            }
        } catch (t: Throwable) {
            Log.d("TAG", "error = ${t.stackTrace}")
            t.printStackTrace()
            onError("error = ${t.message}")
        }
    }
}