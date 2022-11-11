package com.example.moviessearch

data class Movies(
    val id:Int,
    val url:String,
    val title:String,
    val description:String,
    var title_pressed: Boolean = false,
    var isFavorite: Boolean = false
)
