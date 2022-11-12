package com.example.moviessearch.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviessearch.Movies
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.MoviesItemAdapter



class MoviesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitleItem: TextView = itemView.findViewById(R.id.tvTitleItem)
    private val imMovieItem: ImageView = itemView.findViewById(R.id.imMovieItem)
    private val imFavoriteItem: ImageView = itemView.findViewById(R.id.imFavorite)

    fun bind(item: Movies, listener: MoviesItemAdapter.MovieClickListener) {
        tvTitleItem.setText(item.title)
        Glide.with(imMovieItem)
            .load(item.url)
            .into(imMovieItem)

        val color = if (item.title_pressed) ContextCompat.getColor(itemView.context,R.color.pink_2)
        else ContextCompat.getColor(itemView.context, R.color.purple_700)
        tvTitleItem.setTextColor(color)

        if (Repository.isFavorite(item.id)) imFavoriteItem.setImageResource(R.drawable.ic_favorite_red)
        else imFavoriteItem.setImageResource(R.drawable.ic_favorite)

        imMovieItem.setOnClickListener {
            Repository.setPressed(adapterPosition)
            tvTitleItem.setTextColor(ContextCompat.getColor(itemView.context,R.color.pink_2))
            listener.onMovieClick(item, adapterPosition)
        }
        imFavoriteItem.setOnClickListener {
            if (Repository.isFavorite(item.id)) {
                Repository.delFavorite(item.id)
            }
            else {
                Repository.addFavorite(item.id)
            }
            listener.onFavoriteClick(adapterPosition, itemView)
        }
    }
}