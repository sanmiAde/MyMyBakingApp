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
import timber.log.Timber


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
        ingredientsAdapter = IngredientsAdapter()

        binding.rvIngredients.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientsAdapter
        }
        ingredientsAdapter.addIngredients(recipeItem.ingredients)

        stepsAdapter = StepsAdapter { Timber.d(it.description) }

        binding.rvSteps.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = stepsAdapter

        }
        stepsAdapter.addSteps(recipeItem.steps)
    }


}