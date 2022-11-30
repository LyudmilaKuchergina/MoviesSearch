package com.example.moviessearch

import android.util.Log
import com.example.moviessearch.api.MoviesApi
import com.example.moviessearch.db.Db
import com.example.moviessearch.db.MoviesEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    private fun mapFromJson(movies: List<MoviesJson>): List<Movies> {
        return movies.mapIndexed { index, it ->
            Movies(
                id = index,
                url = it.image?.url.orEmpty(),
                title = it.title.orEmpty(),
                description = it.description.orEmpty(),
                year = it.year
            )
        }
    }

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

    private fun saveToDb(movies: List<Movies>) {
        Thread {
            Db.getInstance().getMoviesDao().delete()
            Db.getInstance().getMoviesDao().insert(mapToDb(movies))
        }.start()
    }

    private fun getAllFromDb(): List<Movies>? {
        return mapFromDb(Db.getInstance().getMoviesDao().getAll())
    }

    private fun getFromDbByYear(year: String): List<Movies>? {
        return mapFromDb(Db.getInstance().getMoviesDao().getAllByYear(year))
    }

    private fun getFromDbById(id: Int): MoviesEntity? {
        return Db.getInstance().getMoviesDao().getById(id)
    }

    private fun updateIsFavoriteById(id: Int, isFavorite: Int) {
        Db.getInstance().getMoviesDao().updateIsFavorite(id, isFavorite)
    }

    private fun updateTitlePressed(id: Int) {
        Db.getInstance().getMoviesDao().updateTitlePressed(id)
    }

    fun getMovies(year: String, onReady: (List<Movies>?) -> Unit, onError: (String) -> Unit) {
        Thread {
            val storedMovies = getFromDbByYear(year)
            if (storedMovies?.isEmpty() == true) {
                loadMovies(year, onReady, onError)
            } else {
                onReady(storedMovies)
            }
        }.start()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesApi = retrofit.create(MoviesApi::class.java)

    private fun loadMovies(
        year: String,
        onReady: (List<Movies>) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = moviesApi.getMovies(year).execute()
            val json = response.body()
            val moviesResponse = json?.movies
            Log.d("TAG", "movies(${moviesResponse?.size}) = $moviesResponse")
            moviesResponse?.run {
                val result = mapFromJson(this.filter {
                    it.title != null && it.image?.url != null && it.description != null
                })
                saveToDb(result)
                Log.d("TAG", "movies result size: ${result.size}")
                onReady(result)
            }
        } catch (t: Throwable) {
            Log.d("TAG", "error = ${t.stackTrace}")
            t.printStackTrace()
            onError("error = ${t.message}")
        }
    }

    fun getStoredMovie(num: Int, onReady: (MoviesEntity?) -> Unit) {
        Thread {
            val storedMovie = getFromDbById(num)
            onReady(storedMovie)
        }.start()
    }

    fun setPressed(num: Int) {
        Thread {
            updateTitlePressed(num)
        }.start()
    }

    fun delFavorite(num: Int) {
        Thread {
            updateIsFavoriteById(num, 0)
            listeners.forEach { it.notify(num) }
        }.start()
    }

    fun addFavorite(num: Int) {
        Thread {
            updateIsFavoriteById(num, 1)
            listeners.forEach { it.notify(num) }
        }.start()
    }

    fun getFavorites(onReady: (List<Movies>) -> Unit) {
        Thread {
            onReady(getAllFromDb()?.filter { it.isFavorite } ?: emptyList())
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