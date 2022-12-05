package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository
import com.example.moviessearch.RepositoryProvider

class MoviesListViewModel : ViewModel(), Repository.NotifyListener {

    val repository = RepositoryProvider.repository

    val moviesLiveData = MutableLiveData<List<Movies>>()
    val progressLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()

    init {
        repository.addNotifyListener(this)
        getValues()
    }

    fun getValues() {
        progressLiveData.postValue(true)
        repository.getMovies(year = "2015", onReady = {
            moviesLiveData.postValue(it)
            progressLiveData.postValue(false)
        }, onError = {
            errorLiveData.postValue(it)
            progressLiveData.postValue(false)
        })
    }

    override fun notify(num: Int) {
        getValues()
    }
}