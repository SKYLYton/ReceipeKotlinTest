package com.receipe.fragments.search_recipe

import com.receipe.fragments.search_recipe.model.Recipe
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.receipe.room.model.recipe.RecipeItem
import com.recipes.retrofit.model.recipe.ResultRecipeModel

class SearchModelMapper {
    fun mapToResultSearchRecipe(resultRecipeModel: ResultRecipeModel) : ResultSearchRecipe{
        val resultSearchRecipe = ResultSearchRecipe()
        resultRecipeModel.hits.forEach {
            val recipe = Recipe()
            recipe.calories = it.recipe.calories
            recipe.label = it.recipe.label
            recipe.image = it.recipe.image
            recipe.uri = it.recipe.uri
            recipe.url = it.recipe.url
            resultSearchRecipe.recipes.add(recipe)
        }
        return resultSearchRecipe
    }

    fun mapToResultSearchRecipe(recipeItem: List<RecipeItem>) : ResultSearchRecipe{
        val resultSearchRecipe = ResultSearchRecipe()
        recipeItem.forEach {
            val recipe = Recipe()
            recipe.calories = it.calories
            recipe.label = it.name

            resultSearchRecipe.recipes.add(recipe)
        }
        return resultSearchRecipe
    }

    fun mapToRecipeItems(resultRecipeModel: ResultRecipeModel) : List<RecipeItem>{
        val recipeItems : MutableList<RecipeItem> = mutableListOf()

        resultRecipeModel.hits.forEach {
            val recipeItem = RecipeItem()
            recipeItem.calories = it.recipe.calories
            recipeItem.name = it.recipe.label
            recipeItems.add(recipeItem)
        }

        return recipeItems
    }
}