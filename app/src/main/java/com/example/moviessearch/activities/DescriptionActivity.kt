package com.example.moviessearch.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.moviessearch.R

private const val TAG = "DescriptionActivity"

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_description)

        val imMovie = findViewById<ImageView>(R.id.imMovie)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        imMovie.setImageResource(intent.getIntExtra("extra_movie", 0))
        tvTitle.setText(intent.getIntExtra("extra_title",0))
        tvDescription.setText(intent.getIntExtra("extra_description",0))
    }
}