package com.example.moviessearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

private const val TAG = "DescriptionActivity"

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val imMovie = findViewById<ImageView>(R.id.imMovie)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        imMovie.setImageResource(intent.getIntExtra("extra_movie", 0))
        tvTitle.setText(intent.getIntExtra("extra_title",0))
        tvDescription.setText(intent.getIntExtra("extra_description",0))

        //setResult(RESULT_OK, Intent().putExtra(MainActivity.EXTRA_LIKE,findViewById<CheckBox>(R.id.cbLike).isChecked.toString()))
    }
}