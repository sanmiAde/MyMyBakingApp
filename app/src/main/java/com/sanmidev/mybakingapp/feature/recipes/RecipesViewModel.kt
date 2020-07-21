package com.sanmidev.mybakingapp.feature.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanmidev.mybakingapp.data.remote.repo.BakingRepository
import com.sanmidev.mybakingapp.data.remote.result.BakingRecipeResult
import com.sanmidev.mybakingapp.utils.AppScheduler
import com.sanmidev.mybakingapp.utils.applySchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RecipesViewModel(
    private val bakingRepository: BakingRepository,
    private val scheduler: AppScheduler
) : ViewModel() {

    private val recipesMutableLiveData = MutableLiveData<BakingRecipeResult>()

    private val compositeDisposable = CompositeDisposable()

    val recipesLivaData: LiveData<BakingRecipeResult>
        get() = recipesMutableLiveData

    init {
        getRecipes()
    }

    private fun getRecipes() {

        bakingRepository.getBakingRecipes()
            .applySchedulers(scheduler)
            .doOnSubscribe {
                recipesMutableLiveData.value = BakingRecipeResult.Loading
            }
            .subscribeBy(
                onSuccess = { result: BakingRecipeResult ->
                    recipesMutableLiveData.value = result
                },
                onError = { throwable ->
                    recipesMutableLiveData.value =
                        BakingRecipeResult.Error(throwable.localizedMessage)
                }
            )
    }


    class VMFactory @Inject constructor(
        private val bakingRepository: BakingRepository,
        private val scheduler: AppScheduler
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipesViewModel(bakingRepository, scheduler) as T
        }
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        super.onCleared()
    }
}