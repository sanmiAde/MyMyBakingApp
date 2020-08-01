package com.sanmidev.mybakingapp

import com.sanmidev.mybakingapp.data.remote.BakingService
import com.sanmidev.mybakingapp.data.remote.mapper.BakingRecipeItemModelToEntityMapper
import com.sanmidev.mybakingapp.data.remote.mapper.BakingRecipeModelListToEntityListMapper
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeItemModel
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

object NetworkUtils {

    const val BAKING_RECIPE_PATH = "/baking.json"

    val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    }

    fun provideRetrofit(mockWebServer: MockWebServer): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun provideBakingRecipeService(mockWebServer: MockWebServer): BakingService {
        return provideRetrofit(mockWebServer).create(BakingService::class.java)
    }

    fun getBakingRecipeMockWebserverDispatcher(generatedData: BakingRecipeModelList): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path?.contains(BAKING_RECIPE_PATH)!! -> {

                        val listType = Types.newParameterizedType(
                            List::class.java,
                            BakingRecipeItemModel::class.java
                        )
                        val bakingRecipeJson =
                            moshi.adapter<BakingRecipeModelList>(listType).toJson(generatedData)

                        MockResponse().setBody(
                            bakingRecipeJson
                        ).setResponseCode(HttpURLConnection.HTTP_OK)
                    }
                    else -> {
                        MockResponse().setBody(
                            "Content Not Found"
                        ).setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    }
                }
            }

        }
    }

    fun provideBakingRecipeItemModelToEntityMapper(): BakingRecipeItemModelToEntityMapper {
        return BakingRecipeItemModelToEntityMapper()
    }

    fun provideBakingRecipeModelListToEntityListMapper(): BakingRecipeModelListToEntityListMapper {
        val itemModelToEntityMapper = provideBakingRecipeItemModelToEntityMapper()
        return BakingRecipeModelListToEntityListMapper(itemModelToEntityMapper)
    }
}