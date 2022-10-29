package com.example.moviessearch.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.moviessearch.R
import com.example.moviessearch.fragments.FavoritesFragment
import com.example.moviessearch.fragments.MoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()
{
    private lateinit var moviesFragment: MoviesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(MoviesFragment())
        }

        val navigate: BottomNavigationView = findViewById(R.id.bnView)
        moviesFragment = MoviesFragment()
        navigate.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_movies -> replaceFragment(MoviesFragment())
                R.id.item_favorites -> replaceFragment(FavoritesFragment())
                R.id.item_invite -> inviteFriend()
            }
            true
        }
    }

    fun inviteFriend(){
        val intent = Intent(Intent.ACTION_SEND).setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,"Привет. Не желаешь посмотреть фильм?")
        startActivity(intent)
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentMeneger = supportFragmentManager
        val fragmentTransaction = fragmentMeneger.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment).commit()
    }

    override fun onBackPressed() {
       val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        }
        else {
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

}