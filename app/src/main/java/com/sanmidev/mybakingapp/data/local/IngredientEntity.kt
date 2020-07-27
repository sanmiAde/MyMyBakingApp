package com.sanmidev.mybakingapp.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientEntity(
    val ingredient: String,
    val measure: String,
    val quantity: Double
) : Parcelable