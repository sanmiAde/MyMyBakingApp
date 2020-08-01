package com.sanmidev.mybakingapp.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BakingRecipeItemModel(
    @Json(name = "id")
    val id: Int, // 4
    @Json(name = "image")
    val image: String,
    @Json(name = "ingredients")
    val ingredients: List<IngredientModel>,
    @Json(name = "name")
    val name: String, // Cheesecake
    @Json(name = "servings")
    val servings: Int, // 8
    @Json(name = "steps")
    val steps: List<StepModel>
)