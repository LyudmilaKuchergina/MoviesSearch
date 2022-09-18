package com.example.moviessearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movie
import com.example.moviessearch.viewHolders.MoviesItemViewHolder
import com.example.moviessearch.R

class MoviesItemAdapter(
    private val items: List<Movie>,
    private val listener: MovieClickListener
) : RecyclerView.Adapter<MoviesItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesItemViewHolder {
        return MoviesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesItemViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    interface MovieClickListener{
        fun onMovieClick(moviesItem: Movie, position: Int)
    }

}