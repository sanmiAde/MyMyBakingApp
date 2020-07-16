package com.sanmidev.mybakingapp.data.remote.mapper

import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.data.local.IngredientEntity
import com.sanmidev.mybakingapp.data.local.StepEntity
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeItemModel
import com.sanmidev.mybakingapp.data.remote.model.IngredientModel
import com.sanmidev.mybakingapp.data.remote.model.StepModel
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class BakingRecipeItemModelToEntityMapper @Inject constructor() {
    fun map(bakingRecipeItemModel: BakingRecipeItemModel): BakingRecipeItemEntity {
        val ingredientEntityList =
            bakingRecipeItemModel.ingredients.map { ingredientModelItem: IngredientModel ->
                IngredientEntity(
                    ingredientModelItem.ingredient,
                    ingredientModelItem.measure,
                    ingredientModelItem.quantity
                )
            }

        val stepEntityList = bakingRecipeItemModel.steps.map { stepModelItem ->
            StepEntity(
                stepModelItem.description,
                stepModelItem.id,
                stepModelItem.shortDescription,
                stepModelItem.thumbnailURL,
                stepModelItem.videoURL
            )
        }
        return BakingRecipeItemEntity(
            bakingRecipeItemModel.id,
            bakingRecipeItemModel.image,
            ingredientEntityList,
            bakingRecipeItemModel.name,
            bakingRecipeItemModel.servings,
            stepEntityList
        )
    }
}