package com.example.moviessearch.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "image_url")
    val url:String,
    val title:String,
    val description:String,
    val year:String,
    val title_pressed: Boolean,
    val isFavorite: Boolean
){}

