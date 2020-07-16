package com.sanmidev.mybakingapp.data.remote.mapper

import com.sanmidev.mybakingapp.data.local.BakingRecipeEntityList
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeItemModel
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class BakingRecipeModelListToEntityListMapper @Inject constructor(private val bakingModelToEntityMapper: BakingRecipeItemModelToEntityMapper) {
    fun map(bakingRecipeModelList: BakingRecipeModelList): BakingRecipeEntityList {
        val bakingRecipeItemEntity =
            bakingRecipeModelList.map { bakingRecipeItemModel: BakingRecipeItemModel ->
                bakingModelToEntityMapper.map(bakingRecipeItemModel)
            }

        return BakingRecipeEntityList(bakingRecipeItemEntity)
    }


}