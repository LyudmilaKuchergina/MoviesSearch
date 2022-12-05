package com.example.moviessearch

import com.example.moviessearch.db.Db
import com.example.moviessearch.network.MoviesApiGetter
import com.example.moviessearch.network.RemoteDataSource

object RepositoryProvider {

    val repository by lazy {
        val moviesApiGetter = MoviesApiGetter()
        val remoteDataSource = RemoteDataSource(moviesApiGetter)
        Repository(remoteDataSource, Db)
    }
}