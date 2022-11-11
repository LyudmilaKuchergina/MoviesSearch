package com.example.moviessearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movies
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.viewHolders.FavoritesItemViewHolder

class FavoritesItemAdapter(
    private val listener: FavoritesClickListener
) : RecyclerView.Adapter<FavoritesItemViewHolder>() {

    private var movies: List<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemViewHolder {
        return FavoritesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesItemViewHolder, position: Int) {
        holder.bind(Repository.getFavorites()[position],listener)
    }

    override fun getItemCount(): Int = Repository.getFavorites().size

    fun refreshList(movies: List<Movies>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    interface FavoritesClickListener{
        fun onDeleteClick(id: Int, view: View)
        fun onImageClick(id: Int)
    }
}