package com.example.moviessearch.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.MoviesList
import com.example.moviessearch.api.MoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesViewModelList: ViewModel() {


    val moviesLiveData = MutableLiveData<List<Movies>>()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesApi = retrofit.create(MoviesApi::class.java)

    init {
        getMovies("2020")
    }

    private fun getMovies(year: String) {
        moviesApi.getMovies(year).enqueue(object : Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                val json = response.body()
                val movies = json?.movies
                Log.d("TAG", "movies = $movies")
                movies?.run {
                    moviesLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Log.d("TAG", "error = ${t.stackTrace}")
                t.printStackTrace()
            }
        })
    }

}