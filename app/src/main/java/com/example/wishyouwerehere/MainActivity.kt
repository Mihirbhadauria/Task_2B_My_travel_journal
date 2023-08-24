package com.example.wishyouwerehere

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun setThemePreference(context: Context, isDarkMode: Boolean) {
            val sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("is_dark_mode", isDarkMode).apply()
        }

        fun getThemePreference(context: Context): Boolean {
            val sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean("is_dark_mode", false) // Default is light mode.
        }

        // Apply the theme based on the preference
        if (getThemePreference(this)) {
            setTheme(R.style.Dark)
        } else {
            setTheme(R.style.Light)
        }

        setContentView(R.layout.home_screen)

        val themeToggleButton: Button = findViewById(R.id.toggleThemeButton)
        themeToggleButton.setOnClickListener {
            val isDarkMode = getThemePreference(this)

            // Toggle the theme preference
            setThemePreference(this, !isDarkMode)

            // Recreate the activity to apply the new theme
            recreate()
        }

        val kfcImageView: ImageView = findViewById(R.id.KFC)
        val mcdonaldsImageView: ImageView = findViewById(R.id.imageView3)
        val dominosImageView: ImageView = findViewById(R.id.imageView2)
        val pizzahutImageView: ImageView = findViewById(R.id.imageView4)

        kfcImageView.setOnClickListener {
            val restaurant = Restaurant(
                name = getString(R.string.kfc_title),
                imageResId = R.drawable.kfc
            )
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("restaurant_data", restaurant)
            startActivity(intent)
        }

        mcdonaldsImageView.setOnClickListener {
            val restaurant = Restaurant(
                name = getString(R.string.mcdonalds_title),
                imageResId = R.drawable.mcdonalds_1
            )
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("restaurant_data", restaurant)
            startActivity(intent)
        }

        dominosImageView.setOnClickListener {
            val restaurant = Restaurant(
                name = getString(R.string.dominos_title),
                imageResId = R.drawable.dominos_1
            )
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("restaurant_data", restaurant)
            startActivity(intent)
        }

        pizzahutImageView.setOnClickListener {
            val restaurant = Restaurant(
                name = getString(R.string.pizzahut_title),
                imageResId = R.drawable.pizzahut
            )
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("restaurant_data", restaurant)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateRatingValue()
    }

    private fun updateRatingValue() {
        val tvRatingValue: TextView = findViewById(R.id.dominos_ratingValue)

        // Retrieve and display the saved rating
        val savedRating = PreferenceHelper.getRating(this)
        tvRatingValue.text = "$savedRating"
    }

}

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_view)

        val restaurant: Restaurant? = intent.getParcelableExtra("restaurant_data")

        val imageView: ImageView = findViewById(R.id.sp_dominos_image)
        val nameTextView: TextView = findViewById(R.id.sp_dominos_name)
        val ratingBar: RatingBar = findViewById(R.id.dominos_ratingBar) // Assuming you have a RatingBar with this ID in activity_dominos.xml

        // Set the saved rating to the RatingBar
        ratingBar.rating = PreferenceHelper.getRating(this)

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // Save the new rating
            PreferenceHelper.saveRating(this, rating)
        }

        restaurant?.let {
            imageView.setImageResource(it.imageResId)
            nameTextView.text = it.name
        }
    }
}




