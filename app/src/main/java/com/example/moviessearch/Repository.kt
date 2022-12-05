package com.example.moviessearch

import com.example.moviessearch.db.Db
import com.example.moviessearch.db.MoviesEntity
import com.example.moviessearch.network.RemoteDataSource

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val db: Db
) {

    fun getMovies(year: String, onReady: (List<Movies>?) -> Unit, onError: (String) -> Unit) {
        Thread {
            val storedMovies = db.getByYear(year)
            if (storedMovies?.isEmpty() == true) {
                remoteDataSource.loadMovies(year,
                    onReady = {
                        db.save(it)
                        onReady(it)
                    }, onError
                )
            } else {
                onReady(storedMovies)
            }
        }.start()
    }

    fun getStoredMovie(num: Int, onReady: (MoviesEntity?) -> Unit) {
        Thread {
            val storedMovie = db.getById(num)
            onReady(storedMovie)
        }.start()
    }

    fun setPressed(num: Int) {
        Thread {
            db.updateTitlePressed(num)
        }.start()
    }

    fun delFavorite(num: Int) {
        Thread {
            db.updateIsFavoriteById(num, 0)
            listeners.forEach { it.notify(num) }
        }.start()
    }

    fun addFavorite(num: Int) {
        Thread {
            db.updateIsFavoriteById(num, 1)
            listeners.forEach { it.notify(num) }
        }.start()
    }

    fun getFavorites(onReady: (List<Movies>) -> Unit) {
        Thread {
            onReady(db.getAll()?.filter { it.isFavorite } ?: emptyList())
        }.start()
    }

    private var listeners: MutableSet<NotifyListener> = mutableSetOf()

    fun addNotifyListener(listener: NotifyListener) {
        listeners.add(listener)
    }

    fun deleteNotifyListener(listener: NotifyListener) {
        listeners.add(listener)
    }

    interface NotifyListener {
        fun notify(num: Int)
    }
}