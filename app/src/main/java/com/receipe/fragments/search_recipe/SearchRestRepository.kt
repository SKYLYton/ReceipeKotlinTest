package com.recipes.fragments.search_recipe

import com.recipes.retrofit.model.ApiResponseListener
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import retrofit2.Call

interface SearchRestRepository {
    fun search(q: String, apiResponseListener: ApiResponseListener<ResultRecipeModel?>?) : Call<ResultRecipeModel?>?
}