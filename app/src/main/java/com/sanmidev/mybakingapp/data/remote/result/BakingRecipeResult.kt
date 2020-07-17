package com.sanmidev.mybakingapp.data.remote.result

import com.sanmidev.mybakingapp.data.local.BakingRecipeEntityList

sealed class BakingRecipeResult {

    object Loading : BakingRecipeResult()

    class Success(val data: BakingRecipeEntityList) : BakingRecipeResult()

    class Error(val message: String) : BakingRecipeResult()
}