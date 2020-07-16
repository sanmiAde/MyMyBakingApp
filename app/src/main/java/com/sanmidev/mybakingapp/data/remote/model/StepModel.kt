package com.sanmidev.mybakingapp.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StepModel(
    @Json(name = "description")
    val description: String, // Recipe Introduction
    @Json(name = "id")
    val id: Int, // 0
    @Json(name = "shortDescription")
    val shortDescription: String, // Recipe Introduction
    @Json(name = "thumbnailURL")
    val thumbnailURL: String,
    @Json(name = "videoURL")
    val videoURL: String // https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdae8_-intro-cheesecake/-intro-cheesecake.mp4
)