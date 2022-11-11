package com.example.moviessearch.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviessearch.Movies
import com.example.moviessearch.R
import com.example.moviessearch.adapters.FavoritesItemAdapter

class FavoritesItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val tvTitleItem: TextView = itemView.findViewById(R.id.tvTitleItem)
    private val imMovieItem: ImageView = itemView.findViewById(R.id.imMovieItem)
    private val bDelete: View = itemView.findViewById(R.id.buttonDelete)

    fun bind(item: Movies, listener: FavoritesItemAdapter.FavoritesClickListener) {
        tvTitleItem.setText(item.title)
        Glide.with(imMovieItem)
            .load(item.url)
            .into(imMovieItem)

        bDelete.setOnClickListener {
            listener.onDeleteClick(item.id, itemView)
        }

        imMovieItem.setOnClickListener {
            listener.onImageClick(item.id)
        }
    }
}