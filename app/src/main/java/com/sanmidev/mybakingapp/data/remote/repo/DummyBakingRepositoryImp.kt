package com.sanmidev.mybakingapp.data.remote.repo

import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import io.reactivex.rxjava3.core.Single

class DummyBakingRepositoryImp : BakingRepository {
    override fun getBakingRecipes(): Single<BakingRecipeResult> {
        return Single.just(BakingRecipeResult.Loading)
    }
}