package com.recipes.retrofit.model

import com.recipes.retrofit.model.recipe.ResultRecipeModel
import retrofit2.Call;
import retrofit2.http.*

interface ApiRequest {

    companion object {
        const val URL = "https://api.edamam.com/search/"
    }

    @GET("users/sing-up/")
    fun getRecipes(@Query("q") q: String?): Call<ResultRecipeModel?>?


}