package com.recipes.fragments.search_recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.receipe.App
import com.receipe.fragments.search_recipe.LoaderApi
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    @Inject
    lateinit var loaderApi: LoaderApi

    fun getLiveData() = liveData

    init {
        App.instance.appComponent.inject(this)
    }

    fun search(q: String) {
        loaderApi.getRecipes(q).subscribeOn(Schedulers.io())
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
    }

    fun gelSavedRecipes() {
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
    }
}