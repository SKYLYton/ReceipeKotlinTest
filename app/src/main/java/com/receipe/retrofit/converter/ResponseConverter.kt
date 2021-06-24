package com.receipe.retrofit.converter

import com.recipes.retrofit.model.recipe.ResultRecipeModel
import retrofit2.Response

class ResponseConverter {
    fun convertToResultSearchRecipe(response: Response<ResultRecipeModel>) : ResultRecipeModel {
        if(!response.isSuccessful || response.body() == null) return ResultRecipeModel()
        return response.body()!!
    }
}