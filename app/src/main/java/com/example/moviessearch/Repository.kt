package com.example.moviessearch

object Repository {
    val movies = listOf(
        Movie(
            0,
            R.drawable.klon,
            R.string.klon,
            R.string.klon_content
        ),
        Movie(
            1,
            R.drawable.morbius,
            R.string.morbius,
            R.string.morbius_content
        ),
        Movie(
            2,
            R.drawable.obratimayarealnost,
            R.string.obratimayarealnost,
            R.string.obratimayarealnost_content
        ),
        Movie(
            3,
            R.drawable.padenieluny,
            R.string.padenieluny,
            R.string.padenieluny_content
        ),
        Movie(
            4,
            R.drawable.rodmugskoy,
            R.string.rodmugskoy,
            R.string.rodmugskoy_content
        ),
        Movie(
            5,
            R.drawable.sonic2,
            R.string.sonic2,
            R.string.sonic2_content
        ),
        Movie(
            6,
            R.drawable.superpitomcy,
            R.string.superpitomcy,
            R.string.superpitomcy_content
        ),
        Movie(
            7,
            R.drawable.taynaamuleta,
            R.string.taynaamuleta,
            R.string.taynaamuleta_content
        ),
        Movie(
            8,
            R.drawable.vivarium,
            R.string.vivarium,
            R.string.vivarium_content
        )
    )

    private var listFavorite = mutableListOf<Int>()

    fun delFavorite(num: Int){
        listFavorite.remove(num)
    }

    fun addFavorite(num: Int){
        listFavorite.add(num)
    }

    fun isFavorite(num: Int): Boolean {
        return listFavorite.contains(num)
    }

    fun getFavorites(): List<Movie> {
        return movies.filter { it.id in listFavorite }
    }
}