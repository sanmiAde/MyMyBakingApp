package com.sanmidev.mybakingapp.feature.recipeDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.data.local.StepEntity
import com.sanmidev.mybakingapp.databinding.FragmentDetailBinding
import com.sanmidev.mybakingapp.feature.recipeDetail.adapter.IngredientsAdapter
import com.sanmidev.mybakingapp.feature.recipeDetail.adapter.StepsAdapter
import com.sanmidev.mybakingapp.utils.initToolbarButton
import com.sanmidev.mybakingapp.utils.navigateSafely


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var stepsAdapter: StepsAdapter

    private lateinit var ingredientsAdapter: IngredientsAdapter

    private var recipeDetailBinding: FragmentDetailBinding? = null

    private val binding: FragmentDetailBinding
        get() = recipeDetailBinding!!

    private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeDetailBinding = FragmentDetailBinding.bind(view)
        binding.include.toolbar.title = args.recipeItem.name
        initToolbarButton(requireActivity(), binding.include.toolbar)
        initRecyclerviews(args.recipeItem)

    }


    private fun initRecyclerviews(recipeItem: BakingRecipeItemEntity) {

        binding.rvIngredients.apply {
            ingredientsAdapter = IngredientsAdapter()
            ingredientsAdapter.ingredients = recipeItem.ingredients
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientsAdapter

        }

        binding.rvSteps.apply {
            stepsAdapter = StepsAdapter { stepEntity: StepEntity ->
                val direction =
                    DetailFragmentDirections.actionDetailFragmentToStepFragment(stepEntity)

                findNavController().navigateSafely(direction)
            }
            stepsAdapter.recipeSteps = recipeItem.steps
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = stepsAdapter

        }
    }

}