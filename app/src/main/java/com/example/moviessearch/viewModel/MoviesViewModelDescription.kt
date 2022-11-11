package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository

class MoviesViewModelDescription: ViewModel() {

    val descriptionLiveData = MutableLiveData<Movies>()

    fun getMovie(id: Int) {
        descriptionLiveData.postValue(Repository.getStoredMovie(id))
    }
}