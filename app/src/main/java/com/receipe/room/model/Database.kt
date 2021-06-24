package com.receipe.room.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.receipe.room.model.recipe.RecipeItem
import com.receipe.room.model.recipe.SearchItem

@Database(entities = [RecipeItem::class, SearchItem::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}