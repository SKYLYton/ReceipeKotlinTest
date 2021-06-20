package com.recipes.fragments.search_recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.App
import com.receipe.fragments.search_recipe.SearchLoaderImpl
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.recipes.retrofit.model.ApiResponseListener
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    @Inject
    lateinit var searchLoader: SearchLoaderImpl

    fun getLiveData() = liveData

    init {
        App.instance.appComponent.inject(this)
    }

    fun search(q: String) {
/*        searchRestRepository.search(q, object : ApiResponseListener<ResultRecipeModel?> {
            override fun onSuccess(call: Call<ResultRecipeModel?>?, response: Response<ResultRecipeModel?>?) {
                liveData.value = AppState.Success(response?.body())
            }

            override fun onError(call: Call<ResultRecipeModel?>?, response: Response<ResultRecipeModel?>?) {
                liveData.value = AppState.Error(response?.code())
            }

            override fun onFailure(call: Call<ResultRecipeModel?>?, throwable: Throwable?) {
                liveData.value = AppState.Failure
            }
        })*/

        searchLoader.search(q, object : DisposableSingleObserver<ResultSearchRecipe>() {
            override fun onSuccess(t: ResultSearchRecipe) {
                liveData.value = AppState.Success(t)
            }

            override fun onError(e: Throwable) {
                liveData.value = AppState.Failure
            }
        })
    }
}