package com.sanmidev.mybakingapp.data.remote.repo

import com.google.common.truth.Truth
import com.sanmidev.mybakingapp.NetworkUtils
import com.sanmidev.mybakingapp.TestDataUtil
import com.sanmidev.mybakingapp.data.local.BakingRecipeEntityList
import com.sanmidev.mybakingapp.data.remote.BakingService
import com.sanmidev.mybakingapp.data.remote.mapper.BakingRecipeItemModelToEntityMapper
import com.sanmidev.mybakingapp.data.remote.mapper.BakingRecipeModelListToEntityListMapper
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import com.sanmidev.mybakingapp.utils.TestSchedulers
import com.squareup.moshi.Moshi
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import java.net.HttpURLConnection

class BakingRepositoryImpTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var bakingService: BakingService

    private lateinit var CUT: BakingRepositoryImp

    private lateinit var bakingRecipeModelListToEntityListMapper: BakingRecipeModelListToEntityListMapper

    private lateinit var dispatcher: Dispatcher

    private lateinit var testData: Pair<BakingRecipeModelList, BakingRecipeEntityList>

    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        bakingService = NetworkUtils.provideBakingRecipeService(mockWebServer)

        testData = TestDataUtil.generateBakingRecipeListTestData()

        dispatcher = NetworkUtils.getBakingRecipeMockWebserverDispatcher(testData.first)

        bakingRecipeModelListToEntityListMapper = BakingRecipeModelListToEntityListMapper(
            BakingRecipeItemModelToEntityMapper()
        )

        moshi = NetworkUtils.moshi

        CUT = BakingRepositoryImp(bakingService, bakingRecipeModelListToEntityListMapper, moshi)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getBakingRecipes_shouldReturnBakingRecipeResult_WhenSuccessWhenRequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = dispatcher

        //WHEN
        val testObserver = CUT.getBakingRecipes().test().awaitCount(1)

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        val result = testObserver.values()[0] as BakingRecipeResult.Success
        Truth.assertThat(result.bakingRecipeEntityList).isEqualTo(testData.second)
        testObserver.dispose()
    }


    @Test
    fun getBakingRecipes_shouldReturnBakingRecipeResult_WhenErrorWhenRequestIsNotSuccessful() {
        //GIVEN
        mockWebServer.enqueue(
            MockResponse().setBody(
                "Content Not Found"
            ).setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        )

        //WHEN
        val testObserver = CUT.getBakingRecipes().test().awaitCount(1)

        //THEN
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        val result = testObserver.values()[0] as BakingRecipeResult.Error
        testObserver.dispose()
    }

    @Test
    fun getBakingRecipes_BakingRecipeModelList_callsTheCorrectAPISuccessfully() {
        //GIVEN
        mockWebServer.dispatcher = dispatcher

        //WHEN
        val testObserver = CUT.getBakingRecipes().test().awaitCount(1)
        val request = mockWebServer.takeRequest()

        //THEN
        Truth.assertThat("/baking.json").isEqualTo(request.path)
        Truth.assertThat("GET").isEqualTo(request.method)
        testObserver.dispose()

    }

}
