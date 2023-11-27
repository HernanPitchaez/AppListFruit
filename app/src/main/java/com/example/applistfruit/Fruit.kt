package com.example.applistfruit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit (
    val name: String,
    val nutritions: Nutritions,
    var image: String
): Parcelable

@Parcelize
data class Nutritions (
    val calories: Long,
    val fat: Double,
    val sugar: Double,
    val carbohydrates: Double,
    val protein: Double
): Parcelable
