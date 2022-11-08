package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.MovieDescription
import com.example.moviessearch.api.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesViewModelDescription: ViewModel() {

    val liveData = MutableLiveData<MovieDescription?>()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val MovieApi = retrofit.create(MovieApi::class.java)

    fun getMovie(id: Int) {
        MovieApi.getMovie(id).enqueue(object : Callback<MovieDescription> {
            override fun onResponse(
                call: Call<MovieDescription>,
                response: Response<MovieDescription>
            ) {
                val movieDescription = response.body()
                liveData.postValue(movieDescription)
            }

            override fun onFailure(call: Call<MovieDescription>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}