package com.example.moviessearch.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviessearch.Movies
import com.example.moviessearch.Repository

class FavoritesListViewModel : ViewModel(), Repository.NotifyListener {

    val favoritesLiveData = MutableLiveData<List<Movies>>()

    init {
        Repository.addNotifyListener(this)
        showFavorites()
    }

    private fun showFavorites() {
        Repository.getFavorites(onReady = { movies -> favoritesLiveData.postValue(movies) })
    }

    fun delFavorite(id: Int) {
        Repository.delFavorite(id)
    }

    override fun notify(num: Int) {
        showFavorites()
    }

    override fun onCleared() {
        Repository.deleteNotifyListener(this)
        super.onCleared()
    }
}