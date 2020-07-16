package com.sanmidev.mybakingapp.data.local


data class BakingRecipeItemEntity(
    val id: Int,
    val image: String,
    val ingredients: List<IngredientEntity>,
    val name: String, // Cheesecake
    val servings: Int, // 8
    val steps: List<StepEntity>
)