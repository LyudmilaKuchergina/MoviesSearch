package com.example.moviessearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movies
import com.example.moviessearch.R
import com.example.moviessearch.viewHolders.MoviesItemViewHolder


class MoviesItemAdapter(
    private val listener: MovieClickListener
) : RecyclerView.Adapter<MoviesItemViewHolder>() {

    private var movies: List<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesItemViewHolder {
        return MoviesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesItemViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int = movies.size

    fun refreshList(movies: List<Movies>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    interface MovieClickListener {
        fun onMovieClick(moviesItem: Movies, position: Int)
        fun onFavoriteClick(position: Int, view: View)
    }
}