package com.example.moviessearch.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.moviessearch.Movie
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.MoviesItemAdapter
import com.example.moviessearch.fragments.DescriptionFragment
import com.example.moviessearch.fragments.FavoritesFragment
import com.example.moviessearch.fragments.MoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()//, Repository.NotifyListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigate: BottomNavigationView = findViewById(R.id.bnView)

        replaceFragment(MoviesFragment())

        navigate.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.item_movies -> replaceFragment(MoviesFragment())
                R.id.item_favorites -> replaceFragment(FavoritesFragment())
                R.id.item_description -> replaceFragment(DescriptionFragment())
            }
            true
        }

//        val button = findViewById<Button>(R.id.button_invite)
//            button.setOnClickListener {
//                val intent = Intent(Intent.ACTION_SEND).setType("text/plain")
//                    .putExtra(Intent.EXTRA_TEXT,"Привет. Не желаешь посмотреть фильм?")
//                startActivity(intent)
//            }
//
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentMeneger = supportFragmentManager
        val fragmentTransaction = fragmentMeneger.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment).commit()
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