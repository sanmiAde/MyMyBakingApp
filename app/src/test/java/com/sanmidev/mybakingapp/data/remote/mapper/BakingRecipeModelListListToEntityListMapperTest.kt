package com.sanmidev.mybakingapp.data.remote.mapper

import com.google.common.truth.Truth
import com.sanmidev.mybakingapp.TestDataUtil
import com.sanmidev.mybakingapp.data.local.BakingRecipeEntityList
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import org.junit.Before
import org.junit.Test

class BakingRecipeModelListListToEntityListMapperTest {


    private lateinit var testData: Pair<BakingRecipeModelList, BakingRecipeEntityList>
    private lateinit var CUT: BakingRecipeModelListToEntityListMapper


    @Before
    fun setUp() {
        testData = TestDataUtil.generateBakingRecipeListTestData()
        CUT = BakingRecipeModelListToEntityListMapper(BakingRecipeItemModelToEntityMapper())
    }

    @Test
    fun map_BakingRecipeModelList_shouldReturnBakingRecipeEntityList() {
        //GIVEN
        val bakingRecipeModelList = testData.first
        val bakingRecipeEntityList = testData.second

        //WHEN
        val convertedBakingRecipeEntityList: BakingRecipeEntityList = CUT.map(bakingRecipeModelList)

        //THEN
        Truth.assertThat(convertedBakingRecipeEntityList.data)
            .containsExactlyElementsIn(bakingRecipeEntityList.data)

    }
}