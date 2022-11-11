package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository

class MoviesViewModelList : ViewModel() {

    val moviesLiveData = MutableLiveData<List<Movies>>()

    init {
        getValues()
    }

    private fun getValues() {
        Repository.getMovies(year ="2015", onReady = { moviesLiveData.postValue(it) })
    }
}