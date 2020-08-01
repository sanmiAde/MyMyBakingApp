package com.sanmidev.mybakingapp.data.remote.mapper

import com.sanmidev.mybakingapp.data.local.IngredientEntity
import com.sanmidev.mybakingapp.data.remote.model.IngredientModel
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class IngredienModelToEntityMapper @Inject constructor() {
    fun mapIngredientModelToIngredientEntity(ingredientModel: IngredientModel): IngredientEntity {
        return IngredientEntity(
            ingredientModel.ingredient,
            ingredientModel.measure,
            ingredientModel.quantity
        )
    }
}