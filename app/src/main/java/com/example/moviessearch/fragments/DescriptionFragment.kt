package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviessearch.R
import com.example.moviessearch.Repository
import com.example.moviessearch.viewModel.MoviesViewModelDescription


class DescriptionFragment: Fragment(R.layout.fragment_description){

    lateinit var viewModel: MoviesViewModelDescription

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MoviesViewModelDescription::class.java)

        val value = requireArguments().getInt(VALUE_ARG)

        val imMovie = view.findViewById<ImageView>(R.id.imMovie)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
       //val id = intent.getIntExtra("id",1)
        viewModel.liveData.observe(viewLifecycleOwner, {
            imMovie.setImageResource(Repository.getMovie(value).image_id)
            tvTitle.setText(getText(Repository.getMovie(value).title_id))
            tvDescription.setText(getText(Repository.getMovie(value).description_id))
        })
        viewModel.getMovie(value)
    }

    companion object {
        private const val VALUE_ARG = "value"
        fun newInstance(value: Int): DescriptionFragment {
            val args = Bundle()
            args.putInt(VALUE_ARG, value)
            val fragment = DescriptionFragment()
            fragment.arguments = args
            return fragment
        }
    }
}