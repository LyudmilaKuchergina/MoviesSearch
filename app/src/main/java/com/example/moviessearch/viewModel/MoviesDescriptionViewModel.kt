package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.RepositoryProvider
import com.example.moviessearch.db.MoviesEntity

class MoviesDescriptionViewModel: ViewModel() {

    val descriptionLiveData = MutableLiveData<MoviesEntity>()

    private val repository = RepositoryProvider.repository

    fun getMovie(id: Int) {
        repository.getStoredMovie(id, onReady = {
            descriptionLiveData.postValue(it)
        })
    }
}