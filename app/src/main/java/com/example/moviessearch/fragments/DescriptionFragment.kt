package com.example.moviessearch.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moviessearch.R
import com.example.moviessearch.Repository


class DescriptionFragment: Fragment(R.layout.fragment_description){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = requireArguments().getInt(VALUE_ARG)

        val imMovie = view.findViewById<ImageView>(R.id.imMovie)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        imMovie.setImageResource(Repository.getMovie(value).image_id)
        tvTitle.setText(getText(Repository.getMovie(value).title_id))
        tvDescription.setText(getText(Repository.getMovie(value).description_id))
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