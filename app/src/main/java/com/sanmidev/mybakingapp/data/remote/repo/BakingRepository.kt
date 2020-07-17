package com.sanmidev.mybakingapp.data.remote.repo

import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import io.reactivex.rxjava3.core.Single

interface BakingRepository {

    fun getBakingRecipes(): Single<BakingRecipeResult>
}
