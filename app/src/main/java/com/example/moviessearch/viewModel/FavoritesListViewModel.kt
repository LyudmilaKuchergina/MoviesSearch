package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository
import com.example.moviessearch.RepositoryProvider

class FavoritesListViewModel : ViewModel(), Repository.NotifyListener {

    private val repository = RepositoryProvider.repository

    val favoritesLiveData = MutableLiveData<List<Movies>>()

    init {
        repository.addNotifyListener(this)
        showFavorites()
    }

    private fun showFavorites() {
        repository.getFavorites(onReady = { movies -> favoritesLiveData.postValue(movies) })
    }

    fun delFavorite(id: Int) {
        repository.delFavorite(id)
    }

    override fun notify(num: Int) {
        showFavorites()
    }

    override fun onCleared() {
        repository.deleteNotifyListener(this)
        super.onCleared()
    }
}