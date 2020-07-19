package com.sanmidev.mybakingapp.feature.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.databinding.FragmentRecipesBinding


class RecipesFragment : Fragment() {

    private var fragmentRecipesBinding: FragmentRecipesBinding? = null

    val binding: FragmentRecipesBinding
        get() = fragmentRecipesBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentRecipesBinding = FragmentRecipesBinding.bind(view)
    }


}