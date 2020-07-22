package com.sanmidev.mybakingapp.feature.recipes

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import com.sanmidev.mybakingapp.databinding.FragmentRecipesBinding
import com.sanmidev.mybakingapp.feature.MainActivity
import com.sanmidev.mybakingapp.utils.MarginItemDecoration
import com.sanmidev.mybakingapp.utils.fireToast
import com.sanmidev.mybakingapp.utils.showIf
import com.sanmidev.mybakingapp.utils.showShimmerIf
import timber.log.Timber
import javax.inject.Inject


class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private var fragmentRecipesBinding: FragmentRecipesBinding? = null

    private var recipeAdapter: RecipeListAdapter? = null

    private val binding: FragmentRecipesBinding
        get() = fragmentRecipesBinding!!

    @Inject
    lateinit var vmFactory: RecipesViewModel.VMFactory

    private val viewModel by viewModels<RecipesViewModel> { vmFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentRecipesBinding = FragmentRecipesBinding.bind(view)

        initRecyclerView()
        observeGetRecipes()

    }

    private fun initRecyclerView() {

        recipeAdapter =
            RecipeListAdapter(requireContext()) { bakingItemEntity: BakingRecipeItemEntity ->
                Timber.d(bakingItemEntity.toString())
            }

        binding.recyclerView.apply {
            this.layoutManager = GridLayoutManager(requireContext(), 2)
            this.addItemDecoration(MarginItemDecoration(16))
            this.setHasFixedSize(true)
            this.adapter = recipeAdapter
        }
    }

    private fun observeGetRecipes() {
        viewModel.recipesLivaData.observe(viewLifecycleOwner) { result: BakingRecipeResult ->

            binding.shimmerFrameLayout.showShimmerIf { result is BakingRecipeResult.Loading }
            binding.recyclerView.showIf { result is BakingRecipeResult.Success }
            when (result) {
                is BakingRecipeResult.Success -> {
                    recipeAdapter?.submitList(result.bakingRecipeEntityList.data.toMutableList())

                }
                is BakingRecipeResult.Error -> {
                    fireToast(requireContext(), result.message)
                    Timber.e(result.message)

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}