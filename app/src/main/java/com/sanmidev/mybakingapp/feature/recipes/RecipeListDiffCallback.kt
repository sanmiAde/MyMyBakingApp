package com.sanmidev.mybakingapp.feature.recipes

import androidx.recyclerview.widget.DiffUtil
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity

class RecipeListDiffCallback : DiffUtil.ItemCallback<BakingRecipeItemEntity>() {
    override fun areItemsTheSame(
        oldItem: BakingRecipeItemEntity,
        newItem: BakingRecipeItemEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BakingRecipeItemEntity,
        newItem: BakingRecipeItemEntity
    ): Boolean {
        return oldItem == newItem
    }

}
