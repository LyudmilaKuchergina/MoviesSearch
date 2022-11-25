package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.adapters.FavoritesItemAdapter
import com.example.moviessearch.viewModel.FavoritesListViewModel
import com.google.android.material.snackbar.Snackbar


class FavoritesFragment: Fragment(), FavoritesItemAdapter.FavoritesClickListener{

    lateinit var viewModel : FavoritesListViewModel

    private val adapter = FavoritesItemAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites,container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcView = view.findViewById<RecyclerView>(R.id.rvFavorites)
        rcView.hasFixedSize()
        rcView.layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.list_columns))

        rcView.adapter = adapter

        viewModel = ViewModelProvider(this).get(FavoritesListViewModel::class.java)
        viewModel.favoritesLiveData.observe(viewLifecycleOwner, {
            adapter.setItems(it)
        })

    }

    override fun onDeleteClick(id: Int, view: View) {
        viewModel.delFavorite(id)
        val snackbar = Snackbar.make(view, getString(R.string.notify_delete),Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    override fun onImageClick(id: Int) {
        val fragmentMeneger = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentMeneger?.beginTransaction()
        fragmentTransaction?.add(R.id.container, DescriptionFragment.newInstance(id))
            ?.addToBackStack(null)
            ?.commit()
    }

}