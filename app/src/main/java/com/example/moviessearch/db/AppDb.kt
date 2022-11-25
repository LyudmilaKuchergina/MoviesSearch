package com.example.moviessearch.db

import androidx.room.Database
import androidx.room.RoomDatabase

const val TAG = "AppDb"
@Database(entities = arrayOf(MoviesEntity::class), version=1)
abstract class AppDb: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}