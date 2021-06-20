package com.recipes.fragments.search_recipe

import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.recipes.retrofit.model.ApiResponseListener
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Call

interface SearchLoader {
    fun search(q: String, disposableSingleObserver: DisposableSingleObserver<ResultSearchRecipe>)
}