package com.receipe.retrofit.model

import com.receipe.retrofit.converter.ResponseConverter
import com.recipes.retrofit.model.ApiRequest
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ApiRequestWorkerImpl @Inject constructor(
    var apiRequest: ApiRequest,
    var responseConverter: ResponseConverter
) : ApiRequestWorker {
    override suspend fun getRecipes(q: String?): ResultRecipeModel {
        return apiRequest.getRecipes(q)
    }

/*
    override fun getRecipes(q: String?): Single<ResultRecipeModel> {
        return apiRequest.getRecipes(q).map {
            responseConverter.convertToResultSearchRecipe(it)
        }
    }

    override suspend fun getRecipesOfCoroutines(q: String?): Deferred<ResultRecipeModel> {
        return apiRequest.getRecipesOfCoroutines(q)
    }
}*/

}