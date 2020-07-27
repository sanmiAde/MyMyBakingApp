package com.sanmidev.mybakingapp.feature.recipeDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanmidev.mybakingapp.data.local.IngredientEntity
import com.sanmidev.mybakingapp.databinding.IngredientListItemBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    private val ingredients: MutableList<IngredientEntity> = mutableListOf()

    inner class ViewHolder(private val binding: IngredientListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredientEntity: IngredientEntity) {
            binding.txtIngredient.text = ingredientEntity.ingredient
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: IngredientListItemBinding =
            IngredientListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.bind(ingredient)
    }

    fun addIngredients(ingredientsList: List<IngredientEntity>) {
        ingredients.addAll(ingredientsList)
        notifyDataSetChanged()
    }
}