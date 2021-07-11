package com.receipe.fragments.search_recipe

import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.receipe.retrofit.model.ApiRequestWorker
import com.receipe.room.model.RecipeDao
import com.receipe.room.model.recipe.RecipeItem
import com.receipe.room.model.recipe.SearchItem
import javax.inject.Inject

class LoaderApi @Inject constructor(
    var apiRequestWorker: ApiRequestWorker,
    var mapperSearchModelMapper: SearchModelMapper,
    var recipeDao: RecipeDao
) {

    suspend fun getRecipes(q: String): ResultSearchRecipe {
        val recipes = apiRequestWorker.getRecipes(q)
        val recipeItems = mapperSearchModelMapper.mapToRecipeItems(recipes)
        insertRecipeItems(recipeItems)

        return mapperSearchModelMapper.mapToResultSearchRecipe(recipes)
    }

    suspend fun saveSearchWord(q: String) {
        insertSearchWord(q.lowercase())
    }

    private suspend fun insertSearchWord(q: String) {
        recipeDao.insertSearchWord(SearchItem(name = q))
    }

    private suspend fun insertRecipeItem(recipeItem: RecipeItem) {
        recipeDao.insertRecipeItem(recipeItem)
    }

    private suspend fun insertRecipeItems(recipeItems: List<RecipeItem>) {
        recipeDao.nukeTableRecipeItem()
        recipeDao.insertRecipeItems(recipeItems)
    }

    suspend fun getAllRecipeItem(): ResultSearchRecipe {
        return mapperSearchModelMapper.mapToResultSearchRecipe(recipeDao.getAllRecipeItem())
    }
}