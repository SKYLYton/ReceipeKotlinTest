package com.recipes.retrofit.model.recipe

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("uri")
    @Expose
    val uri: String? = null,

    @SerializedName("label")
    @Expose
    val label: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

    @SerializedName("source")
    @Expose
    val source: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("shareAs")
    @Expose
    val shareAs: String? = null,

    @SerializedName("yield")
    @Expose
    val yield: Double? = null,

    @SerializedName("dietLabels")
    @Expose
    val dietLabels: List<String>? = null,

    @SerializedName("healthLabels")
    @Expose
    val healthLabels: List<String>? = null,

    @SerializedName("cautions")
    @Expose
    val cautions: List<String>? = null,

    @SerializedName("ingredientLines")
    @Expose
    val ingredientLines: List<String>? = null,

    @SerializedName("ingredients")
    @Expose
    val ingredients: List<IngredientModel>? = null,

    @SerializedName("calories")
    @Expose
    val calories: Double? = null,

    @SerializedName("totalWeight")
    @Expose
    val totalWeight: Double? = null,

    @SerializedName("totalTime")
    @Expose
    val totalTime: Double? = null,

    @SerializedName("cuisineType")
    @Expose
    val cuisineType: List<String>? = null,

    @SerializedName("mealType")
    @Expose
    val mealType: List<String>? = null,

    @SerializedName("dishType")
    @Expose
    val dishType: List<String>? = null,

/*    @SerializedName("totalNutrients")
    @Expose
    private val totalNutrients: TotalNutrients? = null,

    @SerializedName("totalDaily")
    @Expose
    private val totalDaily: TotalDaily? = null,

    @SerializedName("digest")
    @Expose
    private val digest: List<Digest>? = null*/
)