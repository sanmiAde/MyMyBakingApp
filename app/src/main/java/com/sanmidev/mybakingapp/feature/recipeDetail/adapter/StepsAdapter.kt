package com.sanmidev.mybakingapp.feature.recipeDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanmidev.mybakingapp.data.local.StepEntity
import com.sanmidev.mybakingapp.databinding.StepListItemBinding


typealias RecipeStepOnClickListener = (stepEntity: StepEntity) -> Unit

class StepsAdapter(val recipeStepOnClickListener: RecipeStepOnClickListener) :
    RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    private val recipeSteps: MutableList<StepEntity> = mutableListOf()

    inner class ViewHolder(private val binding: StepListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stepEntity: StepEntity) {
            binding.txtRecipeStep.text = stepEntity.shortDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = StepListItemBinding.inflate(layoutInflater, parent, false)

        val viewHolder = ViewHolder(binding)

        binding.materialCardView.setOnClickListener {
            val selectedStepEntity = recipeSteps.get(viewHolder.adapterPosition)
            recipeStepOnClickListener.invoke(selectedStepEntity)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = recipeSteps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step: StepEntity = recipeSteps[position]

        holder.bind(step)
    }

    fun addSteps(steps: List<StepEntity>) {
        recipeSteps.addAll(steps)
        notifyDataSetChanged()
    }
}