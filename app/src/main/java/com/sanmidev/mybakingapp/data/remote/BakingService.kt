package com.sanmidev.mybakingapp.data.remote

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface BakingService {

    @GET("baking.json")
    fun getBakingRecipes(): Single<Response<ResponseBody>>
}