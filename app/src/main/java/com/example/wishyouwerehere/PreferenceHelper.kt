package com.example.wishyouwerehere

import android.content.Context

object PreferenceHelper {

    private const val PREFERENCES_NAME = "restaurant_ratings"
    private const val DOMINOS_RATING_KEY = "dominos_rating"
    private const val KFC_RATING_KEY = "kfc_rating"
    private const val MCDONALDS_RATING_KEY = "mcdonalds_rating"
    private const val PIZZAHUT_RATING_KEY = "pizzahut_rating"

    fun saveRating(context: Context, restaurantName: String, rating: Float) {
        val key = when (restaurantName) {
            "Dominos" -> DOMINOS_RATING_KEY
            "KFC" -> KFC_RATING_KEY
            "Mcdonalds" -> MCDONALDS_RATING_KEY
            "Pizzahut" -> PIZZAHUT_RATING_KEY
            else -> return  // Or throw an exception for unknown restaurant name
        }
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putFloat(key, rating).apply()
    }

    fun getRating(context: Context, restaurantName: String): Float {
        val key = when (restaurantName) {
            "Dominos" -> DOMINOS_RATING_KEY
            "KFC" -> KFC_RATING_KEY
            "Mcdonalds" -> MCDONALDS_RATING_KEY
            "Pizzahut" -> PIZZAHUT_RATING_KEY
            else -> return 0f
        }
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getFloat(key, 0f)
    }


}

