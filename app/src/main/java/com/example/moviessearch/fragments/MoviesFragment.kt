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
import com.example.moviessearch.adapters.MoviesItemAdapter
import com.google.android.material.snackbar.Snackbar

class MoviesFragment: Fragment(), Repository.NotifyListener {

    private val adapter = MoviesItemAdapter(object : MoviesItemAdapter.MovieClickListener {
        override fun onMovieClick(moviesItem: Movie, position: Int) {
            val fragmentMeneger = getActivity()?.supportFragmentManager
            val fragmentTransaction = fragmentMeneger?.beginTransaction()
            fragmentTransaction?.add(R.id.container, DescriptionFragment.newInstance(position))
                ?.addToBackStack(null)
                ?.commit()
        }
        override fun onFavoriteClick(position: Int, view: View) {
            val snackbar = Snackbar.make(view, getString(R.string.notify_add), Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        return view
    }

    override fun notify(num: Int) {
        adapter.notifyItemChanged(num)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setItems(Repository.getMovies())

        val rcView = view.findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.list_columns))

        rcView.adapter = adapter
        Repository.setNotifyListner(this)
        rcView.itemAnimator = DefaultItemAnimator()

    }

}