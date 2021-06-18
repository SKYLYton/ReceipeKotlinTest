package com.recipes.fragments.search_recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.App
import com.recipes.retrofit.model.ApiResponseListener
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    @Inject lateinit var searchRestRepository: SearchRestRepositoryImpl

    fun getLiveData() = liveData

    init {
        App.instance.appComponent.inject(this)
    }

    fun search(q: String) {
        // TODO: Сделать запрос с помощью RX
        // TODO: 17.06.2021 Вынести запрос в Loader 
        var call = searchRestRepository.search(q, object : ApiResponseListener<ResultRecipeModel?> {
            override fun onSuccess(call: Call<ResultRecipeModel?>?, response: Response<ResultRecipeModel?>?) {
                liveData.value = AppState.Success(response?.body())
            }

            override fun onError(call: Call<ResultRecipeModel?>?, response: Response<ResultRecipeModel?>?) {
                liveData.value = AppState.Error(response?.code())
            }

            override fun onFailure(call: Call<ResultRecipeModel?>?, throwable: Throwable?) {
                liveData.value = AppState.Failure
            }
        })
    }
}