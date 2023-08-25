package com.example.wishyouwerehere

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            Log.d("DEBUG", "McDonald's image clicked")
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
            Log.d("DEBUG", "pizzahut's image clicked")
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
        updateRatingValues()
    }

    private fun updateRatingValues() {
        val tvDominosRatingValue: TextView = findViewById(R.id.dominos_ratingValue)
        val savedDominosRating = PreferenceHelper.getRating(this, getString(R.string.dominos_title))
        Log.d("DEBUG", "Retrieved rating for Dominos: $savedDominosRating")
        tvDominosRatingValue.text = "$savedDominosRating"

        val tvKFCRatingValue: TextView = findViewById(R.id.kfc_ratingValue)
        val savedKFCRating = PreferenceHelper.getRating(this, getString(R.string.kfc_title))
        Log.d("DEBUG", "Retrieved rating for KFC: $savedKFCRating")
        tvKFCRatingValue.text = "$savedKFCRating"

        val tvMcDonaldsRatingValue: TextView = findViewById(R.id.mcd_ratingValue)
        val savedMcDonaldsRating = PreferenceHelper.getRating(this, getString(R.string.mcdonalds_title))
        Log.d("DEBUG", "Retrieved rating for Mcdonalds: $savedMcDonaldsRating")
        tvMcDonaldsRatingValue.text = "$savedMcDonaldsRating"

        val tvPizzaHutRatingValue: TextView = findViewById(R.id.pizzahut_ratingValue)
        val savedPizzaHutRating = PreferenceHelper.getRating(this, getString(R.string.pizzahut_title))
        Log.d("DEBUG", "Retrieved rating for Pizzahut: $savedPizzaHutRating")
        tvPizzaHutRatingValue.text = "$savedPizzaHutRating"
    }
}

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DEBUG", "DetailsActivity started")
        setContentView(R.layout.detailed_view)

        val restaurant: Restaurant? = intent.getParcelableExtra("restaurant_data")
        val imageView: ImageView = findViewById(R.id.sp_dominos_image)
        val nameTextView: TextView = findViewById(R.id.sp_restaurant_name)
        val ratingBar: RatingBar = findViewById(R.id.dominos_ratingBar)

        restaurant?.let {
            imageView.setImageResource(it.imageResId)
            nameTextView.text = it.name

            // Set the saved rating to the RatingBar
            ratingBar.rating = PreferenceHelper.getRating(this, it.name)

            ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                restaurant?.let {
                    PreferenceHelper.saveRating(this, it.name, rating)
                    Log.d("DEBUG", "Saved rating $rating for ${it.name}")
                }
            }
        }
    }
}




