package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movies
import com.example.moviessearch.viewModel.MoviesViewModelList
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.MoviesItemAdapter
import com.google.android.material.snackbar.Snackbar

class MoviesFragment: Fragment() {//, Repository.NotifyListener {

    lateinit var viewModel : MoviesViewModelList

    private val adapter = MoviesItemAdapter(object : MoviesItemAdapter.MovieClickListener {
        override fun onMovieClick(moviesItem: Movies, position: Int) {
            val fragmentManager = getActivity()?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.add(R.id.container, DescriptionFragment.newInstance(position))
                ?.addToBackStack(null)
                ?.commit()
        }
        override fun onFavoriteClick(position: Int, view: View) {
            val snackbar = Snackbar.make(view, getString(R.string.notify_add), Snackbar.LENGTH_SHORT)
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

//    override fun notify(num: Int) {
//        adapter.notifyItemChanged(num)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //adapter.setItems(Repository.getMovies())

        val rcView = view.findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.list_columns))

        rcView.adapter = adapter
       //Repository.setNotifyListener(this)
        rcView.itemAnimator = DefaultItemAnimator()

        viewModel = ViewModelProvider(this).get(MoviesViewModelList::class.java)
        viewModel.moviesLiveData.observe(viewLifecycleOwner, {
            adapter.refreshList(it)
        })

    }

}