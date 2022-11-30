package com.example.moviessearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movies
import com.example.moviessearch.R
import com.example.moviessearch.viewHolders.FavoritesItemViewHolder

class FavoritesItemAdapter(
    private val listener: FavoritesClickListener
) : RecyclerView.Adapter<FavoritesItemViewHolder>() {

    private var movies: MutableList<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemViewHolder {
        return FavoritesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesItemViewHolder, position: Int) {
        holder.bind(movies[position],listener)
    }

    override fun getItemCount(): Int = movies.size

    interface FavoritesClickListener{
        fun onDeleteClick(id: Int, view: View)
        fun onImageClick(id: Int)
    }

    fun setItems(favorites: List<Movies>){
        val diffCallback = DiffCallback(movies, favorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        movies.clear()
        movies.addAll(favorites)
        diffResult.dispatchUpdatesTo(this)
    }

    private class DiffCallback(
        private val oldList: List<Movies>,
        private val newList: List<Movies>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].isFavorite == newList[newItemPosition].isFavorite &&
                    oldList[oldItemPosition].title_pressed == newList[newItemPosition].title_pressed
        }
    }
}