package com.example.wishyouwerehere

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val name: String,
    val imageResId: Int
) : Parcelable
