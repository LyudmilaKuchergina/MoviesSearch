package com.example.moviessearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val movies = listOf(
        Movie(
            R.drawable.klon,
            R.string.klon,
            R.string.klon_content,
            R.id.tvMovie1,
            R.id.imMovie1,
        ),
        Movie(
            R.drawable.morbius,
            R.string.morbius,
            R.string.morbius_content,
            R.id.tvMovie2,
            R.id.imMovie2,
        ),
        Movie(
            R.drawable.obratimayarealnost,
            R.string.obratimayarealnost,
            R.string.obratimayarealnost_content,
            R.id.tvMovie3,
            R.id.imMovie3,
        ),
        Movie(
            R.drawable.padenieluny,
            R.string.padenieluny,
            R.string.padenieluny_content,
            R.id.tvMovie4,
            R.id.imMovie4,
        ),
        Movie(
            R.drawable.rodmugskoy,
            R.string.rodmugskoy,
            R.string.rodmugskoy_content,
            R.id.tvMovie5,
            R.id.imMovie5,
        ),
        Movie(
            R.drawable.sonic2,
            R.string.sonic2,
            R.string.sonic2_content,
            R.id.tvMovie6,
            R.id.imMovie6,
        ),
        Movie(
            R.drawable.superpitomcy,
            R.string.superpitomcy,
            R.string.superpitomcy_content,
            R.id.tvMovie7,
            R.id.imMovie7,
        ),
        Movie(
            R.drawable.taynaamuleta,
            R.string.taynaamuleta,
            R.string.taynaamuleta_content,
            R.id.tvMovie8,
            R.id.imMovie8,
        ),
        Movie(
            R.drawable.vivarium,
            R.string.vivarium,
            R.string.vivarium_content,
            R.id.tvMovie9,
            R.id.imMovie9,
        )
    )

    companion object{
        const val EXTRA_LIKE = "false"
        const val EXTRA_COMMENT= ""
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d(TAG, "$result")
        result ?: return@registerForActivityResult
            if (result.resultCode == Activity.RESULT_OK){
                result?.data?.extras?.let { extras ->
                    Log.d(TAG,"$(extras.getString(EXTRA_LIKE))" )
                    Log.d(TAG,"$(extras.getString(EXTRA_COMMENT))" )
                }
            }
    }

    private fun OnClickMovie(position: Int) {
        val text = findViewById<TextView>(movies[position].tvMovie)
        text.setTextColor(resources.getColor(R.color.pink_2))
        movies[position].color = resources.getColor(R.color.pink_2)

        launcher.launch(Intent(this,DescriptionActivity::class.java)
            .putExtra("extra_movie", movies[position].image_id)
            .putExtra("extra_title", movies[position].title_id)
            .putExtra("extra_description", movies[position].description_id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in movies.indices) {
            findViewById<ImageView>(movies[i].imMovie).setOnClickListener {
                OnClickMovie(i)
            }
        }
        //Log.d(TAG, "onRestoreInstanceState 0 $savedInstanceState")

        val button = findViewById<Button>(R.id.button_invite)
            button.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT,"Привет. Не желаешь посмотреть фильм?")
                startActivity(intent)
            }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Log.d(TAG, "onRestoreInstanceState 1 $savedInstanceState")
        restoreColors(savedInstanceState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        //Log.d(TAG, "onRestoreInstanceState 2")
        restoreColors(savedInstanceState)
    }

    private fun restoreColors(savedInstanceState: Bundle?) {
        for (i in movies.indices) {
            var color = savedInstanceState?.getInt("key_text_color$i")
                ?: resources.getColor(R.color.purple_700)
            if (color == 0)
               color = resources.getColor(R.color.purple_700)
            findViewById<TextView>(movies[i].tvMovie).setTextColor(color)
            movies[i].color = color
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        for (i in movies.indices) {
            outState.putInt("key_text_color$i", movies[i].color)
        }
        //Log.d(TAG, "onSaveInstanceState 1 $outState")
        super.onSaveInstanceState(outState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        //Log.d(TAG, "onSaveInstanceState 2")
        for (i in movies.indices) {
            outState.putInt("key_text_color$i", movies[i].color)
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }
}