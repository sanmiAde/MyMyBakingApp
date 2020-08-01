package com.sanmidev.mybakingapp

import com.sanmidev.mybakingapp.data.remote.mapper.BakingRecipeModelListToEntityListMapper
import com.sanmidev.mybakingapp.data.remote.repo.BakingRepositoryImp
import com.sanmidev.mybakingapp.utils.TestSchedulers
import okhttp3.mockwebserver.MockWebServer

object RepositoriesFactories {

    fun provideBakingRecipeRepostor(mockWebServer: MockWebServer): BakingRepositoryImp {

        val bakingRecipeService = NetworkUtils.provideBakingRecipeService(mockWebServer)

        val moshi = NetworkUtils.moshi

        val bakingRecipeModelListToEntityListMapper =
            NetworkUtils.provideBakingRecipeModelListToEntityListMapper()

        return BakingRepositoryImp(
            bakingRecipeService,
            bakingRecipeModelListToEntityListMapper,
            moshi
        )
    }

    fun provideTestSchedulers(): TestSchedulers {
        return TestSchedulers()
    }

}