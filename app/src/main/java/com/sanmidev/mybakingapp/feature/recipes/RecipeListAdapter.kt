package com.sanmidev.mybakingapp.feature.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.databinding.RecipeListItemBinding
import com.sanmidev.mybakingapp.utils.GlideApp

typealias RecipeOnClickCallback = (BakingRecipeItemEntity) -> Unit

class RecipeListAdapter(val context: Context, val recipeOnClickCallback: RecipeOnClickCallback) :
    ListAdapter<BakingRecipeItemEntity, RecipeListAdapter.ViewHolder>(RecipeListDiffCallback()) {


    inner class ViewHolder(val binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bakingRecipeItemEntity: BakingRecipeItemEntity) {
            binding.txtRecipeName.text = bakingRecipeItemEntity.name

            GlideApp.with(binding.imgRecipe.context)
                .load(bakingRecipeItemEntity.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.cake)
                .into(binding.imgRecipe)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(context)
        val binding = RecipeListItemBinding.inflate(inflator)

        val viewHolder = ViewHolder(binding)

        binding.root.setOnClickListener {
            recipeOnClickCallback(getItem(viewHolder.adapterPosition))
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    override fun submitList(list: MutableList<BakingRecipeItemEntity>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}