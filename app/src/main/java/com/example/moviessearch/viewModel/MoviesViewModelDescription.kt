package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Repository
import com.example.moviessearch.db.MoviesEntity

class MoviesViewModelDescription: ViewModel() {

    val descriptionLiveData = MutableLiveData<MoviesEntity>()

    fun getMovie(id: Int) {
        Repository.getStoredMovie(id, onReady = {
            descriptionLiveData.postValue(it)
        })
    }
}