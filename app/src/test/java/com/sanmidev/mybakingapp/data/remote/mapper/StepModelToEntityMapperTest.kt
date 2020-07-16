package com.sanmidev.mybakingapp.data.remote.mapper

import com.google.common.truth.Truth
import com.sanmidev.mybakingapp.TestDataUtil
import com.sanmidev.mybakingapp.data.local.StepEntity
import com.sanmidev.mybakingapp.data.remote.model.StepModel
import org.junit.After
import org.junit.Before

import org.junit.Test

class StepModelToEntityMapperTest {

    private lateinit var testData: Pair<StepModel, StepEntity>

    private lateinit var CUT: StepModelToEntityMapper


    @Before
    fun setUp() {
        CUT = StepModelToEntityMapper()
        testData = TestDataUtil.generateStepTestData()
    }

    @Test
    fun mapStepModelToEntity_StepEntity_shouldReturnStepModelWhenSuccessful() {
        //GIVEN
        val stepModel: StepModel = testData.first
        val correctStepEntity: StepEntity = testData.second

        //WHEN
        val convertedStepEntity: StepEntity = CUT.mapStepModelToEntity(stepModel)

        //THEN
        Truth.assertThat(convertedStepEntity.description).isEqualTo(correctStepEntity.description)
        Truth.assertThat(convertedStepEntity.id).isEqualTo(correctStepEntity.id)
        Truth.assertThat(convertedStepEntity.shortDescription)
            .isEqualTo(correctStepEntity.shortDescription)
        Truth.assertThat(convertedStepEntity.thumbnailURL).isEqualTo(correctStepEntity.thumbnailURL)
        Truth.assertThat(convertedStepEntity.videoURL).isEqualTo(correctStepEntity.videoURL)
    }


    @After
    fun tearDown() {
    }
}