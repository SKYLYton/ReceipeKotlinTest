package com.receipe.retrofit.model

import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.Single

interface ApiRequestWorker {
    fun getRecipes(
        q: String?
    ): Single<ResultRecipeModel>
}