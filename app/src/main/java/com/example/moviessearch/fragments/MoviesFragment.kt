package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.Movies
import com.example.moviessearch.R
import com.example.moviessearch.adapters.MoviesItemAdapter
import com.example.moviessearch.viewModel.MoviesListViewModel
import com.google.android.material.snackbar.Snackbar

class MoviesFragment : Fragment(),
    MoviesItemAdapter.MovieClickListener {

    lateinit var viewModel: MoviesListViewModel

    private val adapter = MoviesItemAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcView = view.findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()
        rcView.layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.list_columns))

        rcView.adapter = adapter
        rcView.itemAnimator = DefaultItemAnimator()

        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        viewModel.moviesLiveData.observe(viewLifecycleOwner, {
            adapter.refreshList(it)
        })
        viewModel.progressLiveData.observe(viewLifecycleOwner, {
            setProgressVisibility(it)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            val snackbar = Snackbar.make(view, "Ошибка загрузки фильмов из сети.", Snackbar.LENGTH_INDEFINITE) //$it", Snackbar.LENGTH_INDEFINITE)
                .setAction("Повторить загрузку фильмов?", View.OnClickListener() {

                })
            snackbar.show()
        })
    }

    private fun setProgressVisibility(isVisible: Boolean) {
        val prBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        prBar?.isVisible = isVisible
    }

    override fun onMovieClick(moviesItem: Movies, position: Int) {
        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(R.id.container, DescriptionFragment.newInstance(position))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onFavoriteClick(position: Int, view: View) {
        adapter.notifyItemChanged(position)
        val snackbar = Snackbar.make(view, getString(R.string.notify_add), Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

}