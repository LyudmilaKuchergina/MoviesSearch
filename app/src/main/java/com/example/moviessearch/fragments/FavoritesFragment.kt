package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movie
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.FavoritesItemAdapter
import com.example.moviessearch.adapters.MoviesItemAdapter


class FavoritesFragment: Fragment(), FavoritesItemAdapter.FavoritesClickListener{

    private val adapter = FavoritesItemAdapter(object : FavoritesItemAdapter.FavoritesClickListener {
        override fun onDeleteClick(position: Int) {

        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites,container, false)

        val rcView = view.findViewById<RecyclerView>(R.id.rvFavorites)
        rcView.hasFixedSize()
        rcView.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.list_columns))

        rcView.adapter = adapter

        return view
    }

    override fun onDeleteClick(id: Int) {
        Repository.delFavorite(id)
        //adapter.notifyDataSetChanged()
    }

}