package com.example.moviessearch

data class Movie(
    val id:Int,
    val image_id:Int,
    val title_id:Int,
    val description_id:Int,
    var title_pressed: Boolean = false
)
