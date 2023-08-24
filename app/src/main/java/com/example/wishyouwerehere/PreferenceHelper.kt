package com.example.wishyouwerehere

import android.content.Context

object PreferenceHelper {

    private const val PREFERENCES_NAME = "restaurant_ratings"
    private const val DOMINOS_RATING_KEY = "dominos_ratingBar"

    fun saveRating(context: Context, rating: Float) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putFloat(DOMINOS_RATING_KEY, rating).commit()
    }

    fun getRating(context: Context): Float {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getFloat(DOMINOS_RATING_KEY, 0f)
    }
}
