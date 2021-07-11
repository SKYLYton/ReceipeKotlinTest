package com.receipe.retrofit.model

import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.Single
import kotlinx.coroutines.Deferred

interface ApiRequestWorker {
    suspend fun getRecipes(
        q: String?
    ): ResultRecipeModel

}