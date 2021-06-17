package com.recipes.fragments.search_recipe

import com.recipes.retrofit.RetrofitCallback
import com.recipes.retrofit.model.ApiRequestImpl
import com.recipes.retrofit.model.ApiResponseListener
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import retrofit2.Call
import javax.inject.Inject

class SearchRestRepositoryImpl @Inject constructor(val apiRequestImpl: ApiRequestImpl) : SearchRestRepository {
    override fun search(
        q: String,
        apiResponseListener: ApiResponseListener<ResultRecipeModel?>?
    ): Call<ResultRecipeModel?> {
        val call = apiRequestImpl.apiRequest.getRecipes(q)
        call!!.enqueue(RetrofitCallback(apiResponseListener))
        return call
    }
}