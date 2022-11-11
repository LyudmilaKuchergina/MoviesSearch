package com.example.moviessearch

import android.util.Log
import com.example.moviessearch.api.MoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    private var movies: MutableList<Movies>? = null
    private var storedYear: String = ""

    private fun listTransformation(movies: List<MoviesJson>): List<Movies> {
        return movies.mapIndexed { index, it ->
            Movies(
                id = index,
                url = it.image?.url.orEmpty(),
                title = it.title.orEmpty(),
                description = it.description.orEmpty()
            )
        }
    }

    fun getMovies(year: String, onReady: (List<Movies>) -> Unit) {

        if (storedYear == year && movies != null) {
            onReady(movies!!)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val moviesApi = retrofit.create(MoviesApi::class.java)

        moviesApi.getMovies(year).enqueue(object : Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                val json = response.body()
                val moviesResponse = json?.movies
                Log.d("TAG", "movies(${moviesResponse?.size}) = $moviesResponse")
                moviesResponse?.run {
                    val result = listTransformation(this.filter {
                        it.title != null && it.image?.url != null && it.description != null
                    })
                    movies = result.toMutableList()
                    storedYear = year
                    Log.d("TAG", "movies result size: ${result.size}")
                    onReady(result)
                }
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Log.d("TAG", "error = ${t.stackTrace}")
                t.printStackTrace()
            }
        })
    }

    fun getStoredMovie(num: Int): Movies? {
        return movies?.get(num)
    }

    fun setPressed(num: Int) {
        movies?.get(num)?.title_pressed = true
    }

    fun delFavorite(num: Int) {
        movies?.run {
            this[num] = this[num].copy(isFavorite = false)
        }
        listener?.notify(num)
    }

    fun addFavorite(num: Int) {
        movies?.run {
            this[num] = this[num].copy(isFavorite = true)
        }
        listener?.notify(num)
    }

    fun isFavorite(num: Int): Boolean {
        return movies?.get(num)?.isFavorite ?: false
    }

    fun getFavorites(): List<Movies> {
        return movies?.filter { it.isFavorite } ?: emptyList()
    }

    private var listener: NotifyListener? = null

    fun setNotifyListener(listener: NotifyListener) {
        this.listener = listener
    }

    interface NotifyListener {
        fun notify(num: Int)
    }
}