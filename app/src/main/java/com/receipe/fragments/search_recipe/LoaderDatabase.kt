package com.receipe.fragments.search_recipe

import com.receipe.room.model.RecipeDao
import com.receipe.room.model.recipe.RecipeItem
import com.receipe.room.model.recipe.SearchItem
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoaderDatabase @Inject constructor(var recipeDao: RecipeDao) {


}