package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository

class FavoritesViewModelList : ViewModel() {

    val favoritesLiveData = MutableLiveData<List<Movies>>()

    init {
        getValues()
    }

    private fun getValues() {
        favoritesLiveData.postValue(Repository.getFavorites())
    }
}