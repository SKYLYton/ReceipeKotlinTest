package com.recipes.retrofit.model

import com.receipe.Constants
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.Single
import retrofit2.Call;
import retrofit2.http.*

interface ApiRequest {

    companion object {
        const val URL = "https://api.edamam.com/"
    }

    @GET("search")
    fun getRecipes(
        @Query("q") q: String?,
        @Query("app_id") app_id: String? = Constants.appId,
        @Query("app_key") app_key: String? = Constants.appKey
    ): Single<ResultRecipeModel?>?


}