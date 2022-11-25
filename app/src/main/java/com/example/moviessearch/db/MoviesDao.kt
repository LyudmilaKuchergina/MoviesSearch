package com.example.moviessearch.db

import androidx.room.*

@Dao
interface MoviesDao {

    @Insert
    fun insert(movie: MoviesEntity?)

    @Insert
    fun insert(movies: List<MoviesEntity>)

    @Query("select * from MoviesEntity")
    fun getAll(): List<MoviesEntity>?

    @Query("select * from MoviesEntity where year = :year")
    fun getAllByYear(year: String): List<MoviesEntity>?

    @Query("select * from MoviesEntity where id = :id")
    fun getById(id: Int): MoviesEntity?

    @Query("update MoviesEntity set isFavorite = :isFavorite where  id = :id")
    fun updateIsFavorite(id: Int, isFavorite: Int)

    @Query("update MoviesEntity set title_pressed = 1 where  id = :id")
    fun updateTitlePressed(id: Int)
}