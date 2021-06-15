package com.recipes.retrofit.model.recipe

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientModel (
    @SerializedName("text")
    @Expose
    private val text: String? = null,

    @SerializedName("weight")
    @Expose
    private val weight: Double? = null,

    @SerializedName("foodCategory")
    @Expose
    private val foodCategory: String? = null,

    @SerializedName("foodId")
    @Expose
    private val foodId: String? = null,

    @SerializedName("image")
    @Expose
    private val image: String? = null
)