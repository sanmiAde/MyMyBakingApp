package com.sanmidev.mybakingapp.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepEntity(
    val description: String,
    val id: Int,
    val shortDescription: String,
    val thumbnailURL: String,
    val videoURL: String
) : Parcelable