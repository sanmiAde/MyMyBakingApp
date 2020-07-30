package com.sanmidev.mybakingapp.feature.recipeDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.databinding.FragmentDetailBinding
import com.sanmidev.mybakingapp.feature.recipeDetail.adapter.IngredientsAdapter
import com.sanmidev.mybakingapp.feature.recipeDetail.adapter.StepsAdapter
import com.sanmidev.mybakingapp.utils.fireToast


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
        initRecyclerviews(args.recipeItem)

    }


    private fun initRecyclerviews(recipeItem: BakingRecipeItemEntity) {

        binding.rvIngredients.apply {
            ingredientsAdapter = IngredientsAdapter()
            ingredientsAdapter.addIngredients(recipeItem.ingredients)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientsAdapter

        }

        binding.rvSteps.apply {
            stepsAdapter = StepsAdapter { fireToast(requireContext(), it.description) }
            stepsAdapter.addSteps(recipeItem.steps)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = stepsAdapter

        }
    }

    private fun initToolbar() {
        //binding.
    }
}