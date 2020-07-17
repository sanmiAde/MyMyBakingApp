package com.sanmidev.mybakingapp.data.remote.repo

import com.sanmidev.mybakingapp.data.remote.BakingService
import com.sanmidev.mybakingapp.data.remote.mapper.BakingRecipeModelListToEntityListMapper
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeItemModel
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection
import javax.inject.Inject

class BakingRepositoryImp @Inject constructor(
    private val bakingService: BakingService,
    private val bakingRecipeModelListToEntityListMapper: BakingRecipeModelListToEntityListMapper,
    val moshi: Moshi
) : BakingRepository {
    override fun getBakingRecipes(): Single<BakingRecipeResult> {
        return bakingService.getBakingRecipes()
            .map { responseBody: Response<ResponseBody> ->
                processResponse(responseBody)
            }
    }

    /****
     * This method process the response from the baking recipe API to the corresponding states.
     * if the API status code is equals 200, a [BakingRecipeResult.Success] is sent.
     * Else a [BakingRecipeResult.Error] is sent
     */
    private fun processResponse(responseBody: Response<ResponseBody>): BakingRecipeResult {

        val bakingRecipeResult: BakingRecipeResult = when (responseBody.code()) {
            HttpURLConnection.HTTP_OK -> {

                val responseString = responseBody.body()!!.string()

                val listType =
                    Types.newParameterizedType(List::class.java, BakingRecipeItemModel::class.java)

                //TODO refactor this. Is there really a need for a class that inherits ArrayList<BakingRecipeItemModel>
                val recipes: List<BakingRecipeItemModel> =
                    moshi.adapter<List<BakingRecipeItemModel>>(listType).fromJson(responseString)!!

                val bakingRecipeModelList = BakingRecipeModelList()
                bakingRecipeModelList.addAll(recipes)

                val data = bakingRecipeModelListToEntityListMapper.map(bakingRecipeModelList)

                BakingRecipeResult.Success(data)


            }
            else -> BakingRecipeResult.Error("Could not get list baking recipes")
        }

        return bakingRecipeResult
    }

}