package com.receipe.fragments.search_recipe

import com.receipe.App
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.receipe.retrofit.model.ApiRequestWorker
import com.receipe.room.model.RecipeDao
import com.receipe.room.model.recipe.RecipeItem
import com.receipe.room.model.recipe.SearchItem
import com.recipes.retrofit.model.ApiRequest
import com.recipes.retrofit.model.recipe.ResultRecipeModel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.flowables.ConnectableFlowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoaderApi @Inject constructor(
    var apiRequestWorker: ApiRequestWorker,
    var mapperSearchModelMapper: SearchModelMapper,
    var loaderDatabase: LoaderDatabase,
    var recipeDao: RecipeDao
) {

    fun getRecipes(q: String): Single<ResultSearchRecipe> {
        saveSearchWord(q)

        return apiRequestWorker.getRecipes(q)
            .map {
                val recipeItems = mapperSearchModelMapper.mapToRecipeItems(it)
                insertRecipeItems(recipeItems)
            }.map {
                getAllRecipeItem()
            }
    }

    private fun saveSearchWord(q: String) {
        Flowable.fromCallable {
            insertSearchWord(q)
        }.subscribeOn(Schedulers.io())
            .publish()
            .connect()
    }

    private fun insertSearchWord(q: String) {
        recipeDao.insertSearchWord(SearchItem(name = q))
    }

    private fun insertRecipeItem(recipeItem: RecipeItem) {
        recipeDao.insertRecipeItem(recipeItem)
    }

    private fun insertRecipeItems(recipeItems: List<RecipeItem>) {
        if (recipeItems.isEmpty()) return
        recipeDao.nukeTableRecipeItem()
        recipeItems.forEach {
            insertRecipeItem(it)
        }
    }

    fun getAllRecipeItem(): ResultSearchRecipe {
        return mapperSearchModelMapper.mapToResultSearchRecipe(recipeDao.getAllRecipeItem())
    }
}