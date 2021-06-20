package com.receipe.fragments.search_recipe.model

import com.recipes.retrofit.model.recipe.IngredientModel

data class Recipe(
    var uri: String? = null,
    var label: String? = null,
    var image: String? = null,
    var url: String? = null,
    var calories: Double? = null,
)
