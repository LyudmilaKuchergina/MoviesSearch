package com.example.moviessearch.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.FavoritesItemAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager


class FavoritesActivity : AppCompatActivity(), FavoritesItemAdapter.FavoritesClickListener {

    private val adapter = FavoritesItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val rcView = findViewById<RecyclerView>(R.id.rvFavorites)
        rcView.hasFixedSize()

        rcView.setLayoutManager(GridLayoutManager(this, resources.getInteger(R.integer.list_columns)))
        rcView.adapter = adapter
    }

    override fun onDeleteClick(id: Int) {
        Repository.delFavorite(id)
        adapter.notifyDataSetChanged()
    }
}