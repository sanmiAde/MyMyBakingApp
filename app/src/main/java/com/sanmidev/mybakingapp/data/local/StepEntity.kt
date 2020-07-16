package com.sanmidev.mybakingapp.data.local

import com.squareup.moshi.Json

data class StepEntity(
    val description: String,
    val id: Int,
    val shortDescription: String,
    val thumbnailURL: String,
    val videoURL: String
)