package com.sanmidev.mybakingapp.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientModel(
    @Json(name = "ingredient")
    val ingredient: String, // Graham Cracker crumbs
    @Json(name = "measure")
    val measure: String, // CUP
    @Json(name = "quantity")
    val quantity: Double // 2
)