package com.example.moviessearch.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import com.example.moviessearch.Movie
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.MoviesItemAdapter

class MainActivity : AppCompatActivity(), Repository.NotifyListener {

    private val adapter = MoviesItemAdapter(object : MoviesItemAdapter.MovieClickListener {
        override fun onMovieClick(moviesItem: Movie, position: Int) {
            val intent = Intent(this@MainActivity, DescriptionActivity::class.java)
                .putExtra("extra_movie", moviesItem.image_id)
                .putExtra("extra_title", moviesItem.title_id)
                .putExtra("extra_description", moviesItem.description_id)
            startActivity(intent)
        }
    })

    override fun notify(num: Int) {
        adapter.notifyItemChanged(num)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.setLayoutManager(GridLayoutManager(this, resources.getInteger(R.integer.list_columns)))

        rcView.adapter = adapter
        Repository.setNotifyListner(this)
        rcView.setItemAnimator(DefaultItemAnimator())

        val button = findViewById<Button>(R.id.button_invite)
            button.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT,"Привет. Не желаешь посмотреть фильм?")
                startActivity(intent)
            }

        val button_favorites = findViewById<Button>(R.id.button_favorite)
        button_favorites.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setItems(Repository.getMovies())
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle(resources.getString(R.string.confirm))
            setMessage(resources.getString(R.string.question_exit))

            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                super.onBackPressed()
            }

            setNegativeButton(resources.getString(R.string.no)){_, _ ->

            }
            setCancelable(true)
        }.create().show()
    }

}