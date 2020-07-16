package com.sanmidev.mybakingapp.data.remote.mapper

import com.google.common.truth.Truth
import com.sanmidev.mybakingapp.TestDataUtil
import com.sanmidev.mybakingapp.data.local.IngredientEntity
import com.sanmidev.mybakingapp.data.remote.model.IngredientModel
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class IngredienModelToEntityMapperTest {

    private lateinit var testData: Pair<IngredientModel, IngredientEntity>
    private lateinit var CUT: IngredienModelToEntityMapper

    @Before
    fun setUp() {
        testData = TestDataUtil.generateIngredientTestData()
        CUT = IngredienModelToEntityMapper()
    }


    @Test
    fun mapIngredientModelToIngredientEntity_IngredienModel_shouldReturnIngrdientEntityWhenSuccessful() {
        //GIVEN
        val ingredientModel = testData.first
        val ingredientEntity = testData.second


        //WHEN
        val convertedIngredientEntity: IngredientEntity =
            CUT.mapIngredientModelToIngredientEntity(ingredientModel)

        //THEN
        Truth.assertThat(convertedIngredientEntity.ingredient)
            .isEqualTo(ingredientEntity.ingredient)
        Truth.assertThat(convertedIngredientEntity.measure).isEqualTo(ingredientEntity.measure)
        Truth.assertThat(convertedIngredientEntity.quantity).isEqualTo(ingredientEntity.quantity)

    }
}