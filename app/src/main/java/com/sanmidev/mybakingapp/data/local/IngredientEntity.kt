package com.sanmidev.mybakingapp.data.local

import com.squareup.moshi.Json

data class IngredientEntity(
    val ingredient: String,
    val measure: String,
    val quantity: Int
)