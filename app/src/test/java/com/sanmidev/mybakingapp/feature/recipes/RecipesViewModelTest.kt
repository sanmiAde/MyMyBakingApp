package com.sanmidev.mybakingapp.feature.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.jraska.livedata.test
import com.nhaarman.mockito_kotlin.verify

import com.sanmidev.mybakingapp.NetworkUtils
import com.sanmidev.mybakingapp.RepositoriesFactories
import com.sanmidev.mybakingapp.TestDataUtil
import com.sanmidev.mybakingapp.data.local.BakingRecipeEntityList
import com.sanmidev.mybakingapp.data.remote.model.BakingRecipeModelList
import com.sanmidev.mybakingapp.data.remote.repo.BakingRepositoryImp
import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import com.sanmidev.mybakingapp.utils.TestSchedulers
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class RecipesViewModelTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var CUT: RecipesViewModel

    private lateinit var bakingRepo: BakingRepositoryImp

    private lateinit var testSchedulers: TestSchedulers

    private lateinit var dispatcher: Dispatcher

    private lateinit var testData: Pair<BakingRecipeModelList, BakingRecipeEntityList>

    @Mock
    lateinit var observer: Observer<BakingRecipeResult>

    @Before
    fun setUp() {
        bakingRepo = RepositoriesFactories.provideBakingRecipeRepostor(mockWebServer)
        testSchedulers = RepositoriesFactories.provideTestSchedulers()
        testData = TestDataUtil.generateBakingRecipeListTestData()
        dispatcher = NetworkUtils.getBakingRecipeMockWebserverDispatcher(testData.first)


    }

    @After
    fun tearDown() {
    }


    @Test
    fun getRecipesLivaData_ShouldReturnSuccessLivedata_WhenRequestIsSuccessful() {
        //GIVEN
        mockWebServer.dispatcher = dispatcher

        //THEN
        CUT = RecipesViewModel(bakingRepo, testSchedulers)


        //WHEN
        val testObserver = CUT.recipesLivaData.test()

        testObserver.assertValue {
            it::class.java == BakingRecipeResult.Loading::class.java
        }

        testObserver.awaitNextValue()
        testObserver.assertValue {
            it::class.java == BakingRecipeResult.Success::class.java
        }


        val value = testObserver.value() as BakingRecipeResult.Success
        Truth.assertThat(value.data.data).containsExactlyElementsIn(testData.second.data)


    }


    @Test
    fun getRecipesLivaData_ShouldReturnErrorLivedata_WhenRequestIsNotSuccessful() {
        //GIVEN
        mockWebServer.enqueue(
            MockResponse().setBody(
                "Content Not Found"
            ).setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        )

        //THEN
        CUT = RecipesViewModel(bakingRepo, testSchedulers)


        //WHEN
        val testObserver = CUT.recipesLivaData.test()

        testObserver.assertValue {
            it::class.java == BakingRecipeResult.Loading::class.java
        }

        testObserver.awaitNextValue()
        testObserver.assertValue {
            it::class.java == BakingRecipeResult.Error::class.java
        }


        val value = testObserver.value() as BakingRecipeResult.Error
        Truth.assertThat(value.message).contains("Could not get list baking recipes")


    }
}