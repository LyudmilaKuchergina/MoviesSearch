package com.example.moviessearch.db

import android.content.Context
import androidx.room.Room
import com.example.moviessearch.Movies

object Db {

    private var INSTANCE: AppDb? = null

    fun setupDb(context: Context) {
        if (INSTANCE == null) {
            synchronized(AppDb::class) {
                INSTANCE = Room.databaseBuilder(context, AppDb::class.java, "db-name.db")
                    .build()
            }
        }
    }

    private fun getInstance(): AppDb = INSTANCE!!

    private fun mapFromDb(movies: List<MoviesEntity>?): List<Movies>? {
        return movies?.mapIndexed { index, it ->
            Movies(
                id = it.id,
                url = it.url,
                title = it.title,
                description = it.description,
                year = it.year,
                title_pressed = it.title_pressed,
                isFavorite = it.isFavorite
            )
        }
    }

    fun getAll(): List<Movies>? {
        return mapFromDb(getInstance().getMoviesDao().getAll())
    }

    fun getByYear(year: String): List<Movies>? {
        return mapFromDb(getInstance().getMoviesDao().getAllByYear(year))
    }

    fun getById(id: Int): MoviesEntity? {
        return getInstance().getMoviesDao().getById(id)
    }

    fun updateIsFavoriteById(id: Int, isFavorite: Int) {
        getInstance().getMoviesDao().updateIsFavorite(id, isFavorite)
    }

    fun updateTitlePressed(id: Int) {
        getInstance().getMoviesDao().updateTitlePressed(id)
    }

    private fun mapToDb(movies: List<Movies>): List<MoviesEntity> {
        return movies.mapIndexed { index, it ->
            MoviesEntity(
                id = it.id,
                url = it.url,
                title = it.title,
                description = it.description,
                year = it.year,
                title_pressed = it.title_pressed,
                isFavorite = it.isFavorite
            )
        }
    }

    fun save(movies: List<Movies>) {
        Thread {
            getInstance().getMoviesDao().delete()
            getInstance().getMoviesDao().insert(mapToDb(movies))
        }.start()
    }
}