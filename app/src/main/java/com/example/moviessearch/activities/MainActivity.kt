package com.example.moviessearch.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import com.example.moviessearch.Movie
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.MoviesItemAdapter

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_LIKE = "false"
        const val EXTRA_COMMENT= ""
    }

    private val adapter = MoviesItemAdapter(Repository.movies, object : MoviesItemAdapter.MovieClickListener {
        override fun onMovieClick(moviesItem: Movie, position: Int) {
            val intent = Intent(this@MainActivity, DescriptionActivity::class.java)
                .putExtra("extra_movie", moviesItem.image_id)
                .putExtra("extra_title", moviesItem.title_id)
                .putExtra("extra_description", moviesItem.description_id)
            startActivity(intent)
        }
    } )

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d(TAG, "$result")
        result ?: return@registerForActivityResult
            if (result.resultCode == Activity.RESULT_OK){
                result.data?.extras?.let { extras ->
                    Log.d(TAG,"$(extras.getString(EXTRA_LIKE))" )
                    Log.d(TAG,"$(extras.getString(EXTRA_COMMENT))" )
                }
            }
    }
    val repository = Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.setLayoutManager(GridLayoutManager(this, resources.getInteger(R.integer.list_columns)))

        rcView.adapter = adapter
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
        adapter.notifyDataSetChanged()
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