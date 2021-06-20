package com.receipe.fragments.search_recipe

import com.receipe.fragments.search_recipe.model.Recipe
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.recipes.fragments.search_recipe.SearchLoader
import com.recipes.retrofit.model.ApiRequestImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchLoaderImpl @Inject constructor(val apiRequestImpl: ApiRequestImpl) :
    SearchLoader {

    override fun search(
        q: String,
        disposableSingleObserver: DisposableSingleObserver<ResultSearchRecipe>
    ) {
        apiRequestImpl
            ._apiRequest.getRecipes(q)
            ?.subscribeOn(Schedulers.io())
            ?.map { it ->
                val resultSearchRecipe = ResultSearchRecipe()
                it.hits.forEach {
                    val recipe = Recipe()
                    recipe.calories = it.recipe.calories
                    recipe.label = it.recipe.label
                    recipe.image = it.recipe.image
                    recipe.uri = it.recipe.uri
                    recipe.url = it.recipe.url
                    resultSearchRecipe.recipes.add(recipe)
                }
                resultSearchRecipe
            }
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(disposableSingleObserver)
    }
}