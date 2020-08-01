package com.sanmidev.mybakingapp.data.remote.mapper

import com.sanmidev.mybakingapp.data.local.StepEntity
import com.sanmidev.mybakingapp.data.remote.model.StepModel
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class StepModelToEntityMapper @Inject constructor() {
    fun mapStepModelToEntity(stepModel: StepModel): StepEntity {
        return StepEntity(
            stepModel.description,
            stepModel.id,
            stepModel.shortDescription,
            stepModel.thumbnailURL,
            stepModel.videoURL
        )
    }
}