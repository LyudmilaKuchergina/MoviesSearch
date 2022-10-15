package com.example.moviessearch.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.moviessearch.activities.DescriptionActivity
import com.example.moviessearch.adapters.MoviesItemAdapter

class MoviesFragment: Fragment(), Repository.NotifyListener {

    private val adapter = MoviesItemAdapter(object : MoviesItemAdapter.MovieClickListener {
        override fun onMovieClick(moviesItem: Movie, position: Int) {

        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        val rcView = view.findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.list_columns))

        rcView.adapter = adapter
        Repository.setNotifyListner(this)
        rcView.itemAnimator = DefaultItemAnimator()

        return view
    }

    override fun notify(num: Int) {
        adapter.notifyItemChanged(num)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated")
        adapter.setItems(Repository.getMovies())
    }

}