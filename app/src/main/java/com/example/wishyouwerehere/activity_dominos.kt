package com.example.wishyouwerehere

import android.os.Bundle
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity

class activity_dominos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_view)

        val ratingBar: RatingBar = findViewById(R.id.dominos_ratingBar)

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->

            PreferenceHelper.saveRating(this, rating)
        }
    }
}
