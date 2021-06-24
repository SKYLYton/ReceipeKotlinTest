package com.recipes.retrofit.model.recipe

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hit (
    @SerializedName("recipe")
    @Expose
    val recipe: RecipeModel
)