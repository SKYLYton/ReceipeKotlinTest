package com.recipes.fragments.search_recipe

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.receipe.BaseViewModel
import com.receipe.fragments.search_recipe.LoaderApi
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(var loaderApi: LoaderApi) :
    BaseViewModel() {

    val liveData: MutableLiveData<AppState> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun search(q: String) {

/*        loaderApi.saveSearchWord(q)
            .subscribeOn(Schedulers.io())
            .subscribe({}, {
                it.message?.let { it1 -> Log.e("SearchViewModel", it1) }
            })

        loaderApi.getRecipes(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.recipes.isNotEmpty()) {
                    liveData.value = AppState.Success(it)
                }
            }, {
                liveData.value = AppState.Error(0)
            })*/
        doWork {
            val result = loaderApi.getRecipes(q)

            if(result.recipes.isNotEmpty()){
                liveData.postValue(AppState.Success(result))
            } else {
                liveData.postValue(AppState.Error(1))
            }
        }
        doWork {
            loaderApi.saveSearchWord(q)
        }
    }

    fun getSavedRecipes() {
        doWork {
            val result = loaderApi.getAllRecipeItem()
            liveData.postValue(AppState.Success(result))
        }
    }

/*    fun getSavedRecipes() {
        Single.fromCallable {
            loaderApi.getAllRecipeItem()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ResultSearchRecipe>() {
                override fun onSuccess(t: ResultSearchRecipe) {
                    if (t.recipes.isEmpty()) return
                    liveData.value = AppState.Success(t)
                }

                override fun onError(e: Throwable) {
                    liveData.value = AppState.Error(0)
                }

            })
    }*/

    override fun onStart() {

    }
}