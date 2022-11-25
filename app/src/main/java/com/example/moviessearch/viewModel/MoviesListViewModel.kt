package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository

class MoviesListViewModel : ViewModel(), Repository.NotifyListener {

    val moviesLiveData = MutableLiveData<List<Movies>>()
    val progressLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()

    init {
        Repository.addNotifyListener(this)
        getValues()
    }

    private fun getValues() {
        progressLiveData.postValue(true)
        Repository.getMovies(year = "2015", onReady = {
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