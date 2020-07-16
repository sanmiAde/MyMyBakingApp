package com.sanmidev.mybakingapp.data.remote.mapper

import com.google.common.truth.Truth
import com.sanmidev.mybakingapp.TestDataUtil
import com.sanmidev.mybakingapp.data.local.BakingRecipeItemEntity
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeItemModel
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class BakingRecipeItemModelToEntityMapperTest {

    private lateinit var testData: Pair<BakingRecipeItemModel, BakingRecipeItemEntity>
    private lateinit var CUT: BakingRecipeItemModelToEntityMapper

    @Before
    fun setUp() {
        testData = TestDataUtil.generateBakingRecipeTestData()
        CUT = BakingRecipeItemModelToEntityMapper()
    }


    @Test
    fun map_BakingRecipeModel_shouldReturnBakingRecipeEntityWhenSuccessful() {
        //GIVEN
        val bakingRecipeItemModel = testData.first
        val bakingRecipeItemEntity = testData.second


        //WHEN
        val convertedBakingRecipeItemEntity: BakingRecipeItemEntity = CUT.map(bakingRecipeItemModel)

        //THEN
        Truth.assertThat(convertedBakingRecipeItemEntity.id).isEqualTo(bakingRecipeItemEntity.id)
        Truth.assertThat(convertedBakingRecipeItemEntity.image)
            .isEqualTo(bakingRecipeItemEntity.image)
        Truth.assertThat(convertedBakingRecipeItemEntity.ingredients)
            .containsExactlyElementsIn(bakingRecipeItemEntity.ingredients)
        Truth.assertThat(convertedBakingRecipeItemEntity.servings)
            .isEqualTo(bakingRecipeItemEntity.servings)
        Truth.assertThat(convertedBakingRecipeItemEntity.steps)
            .containsExactlyElementsIn(bakingRecipeItemEntity.steps)
    }
}