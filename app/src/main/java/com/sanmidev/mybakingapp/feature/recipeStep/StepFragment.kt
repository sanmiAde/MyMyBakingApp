package com.sanmidev.mybakingapp.feature.recipeStep

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.databinding.FragmentStepBinding
import com.sanmidev.mybakingapp.utils.fireToast
import com.sanmidev.mybakingapp.utils.initToolbarButton


class StepFragment : Fragment(R.layout.fragment_step) {

    private var fragmentStepBinding: FragmentStepBinding? = null

    private val binding: FragmentStepBinding
        get() = fragmentStepBinding!!

    private val args by navArgs<StepFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentStepBinding = FragmentStepBinding.bind(view)
        with(binding.toolbarLayout.toolbar) {
            initToolbarButton(requireActivity(), this)
            this.title = context.getString(R.string.txt_step_desc)
        }

        binding.txtDescription.text = args.stepEntity.description

        val exoComponent = ExoComponent(requireContext(), this.lifecycle, binding.exoPlayer) {
            fireToast(requireContext(), "Sorry, an error occured while playing this step.")
        }

        exoComponent.initPlayer(args.stepEntity.videoURL)
    }

    override fun onDestroyView() {
        fragmentStepBinding = null
        super.onDestroyView()
    }
}