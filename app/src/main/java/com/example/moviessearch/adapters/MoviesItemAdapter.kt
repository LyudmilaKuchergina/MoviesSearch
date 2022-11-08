package com.example.moviessearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

//    fun setItems(items: List<Movie>) {
//        val diffCallback = DiffCallback(movies, items)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//
//        movies.clear()
//        movies.addAll(items)
//        diffResult.dispatchUpdatesTo(this)
//    }

    interface MovieClickListener {
        fun onMovieClick(moviesItem: Movies, position: Int)
        fun onFavoriteClick(position: Int, view: View)
    }

//    private class DiffCallback(
//        private val oldList: List<Movies>,
//        private val newList: List<Movies>
//    ) : DiffUtil.Callback() {
//
//        override fun getOldListSize() = oldList.size
//
//        override fun getNewListSize() = newList.size
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return oldList[oldItemPosition].id == newList[newItemPosition].id
//        }
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return oldList[oldItemPosition].isFavorite == newList[newItemPosition].isFavorite &&
//                    oldList[oldItemPosition].title_pressed == newList[newItemPosition].title_pressed
//        }
//    }
}