package com.receipe.fragments.search_recipe.model


data class ResultSearchRecipe(
    var recipes: MutableList<Recipe> = mutableListOf<Recipe>()
)
