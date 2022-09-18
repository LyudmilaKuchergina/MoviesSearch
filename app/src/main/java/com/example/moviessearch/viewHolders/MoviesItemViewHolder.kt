package com.example.moviessearch.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movie
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.MoviesItemAdapter

class MoviesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitleItem: TextView = itemView.findViewById(R.id.tvTitleItem)
    private val imMovieItem: ImageView = itemView.findViewById(R.id.imMovieItem)
    private val imFavoriteItem: ImageView = itemView.findViewById(R.id.imFavorite)

    fun bind(item: Movie, listener: MoviesItemAdapter.MovieClickListener) {
        tvTitleItem.setText(item.title_id)
        imMovieItem.setImageResource(item.image_id)

        val color = if (item.title_pressed) itemView.context.resources.getColor(R.color.pink_2)
        else itemView.context.resources.getColor(R.color.purple_700)
        tvTitleItem.setTextColor(color)

        if (Repository.isFavorite(item.id)) imFavoriteItem.setImageResource(R.drawable.ic_favorite_red)
        else imFavoriteItem.setImageResource(R.drawable.ic_favorite)

        imMovieItem.setOnClickListener {
            Repository.movies[position].title_pressed = true
            tvTitleItem.setTextColor(itemView.context.resources.getColor(R.color.pink_2))
            listener.onMovieClick(item, adapterPosition)
        }
        imFavoriteItem.setOnClickListener {
            imFavoriteItem.setImageResource(R.drawable.ic_favorite_red)
            Repository.addFavorite(item.id)
        }
    }
}