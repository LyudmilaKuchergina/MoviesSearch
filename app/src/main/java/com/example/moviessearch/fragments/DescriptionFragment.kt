package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviessearch.R
import com.example.moviessearch.viewModel.MoviesDescriptionViewModel


class DescriptionFragment: Fragment(R.layout.fragment_description){

    lateinit var descriptionViewModel: MoviesDescriptionViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        descriptionViewModel = ViewModelProvider(this).get(MoviesDescriptionViewModel::class.java)

        val value = requireArguments().getInt(VALUE_ARG)

        val imMovie = view.findViewById<ImageView>(R.id.imMovie)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        descriptionViewModel.descriptionLiveData.observe(viewLifecycleOwner, {
            Glide.with(imMovie)
                .load(it.url)
                .into(imMovie)
            tvTitle.text = it.title
            tvDescription.text = it.description
        })
        descriptionViewModel.getMovie(value)
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