package com.receipe.room.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.receipe.room.model.recipe.RecipeItem
import com.receipe.room.model.recipe.SearchItem
import io.reactivex.Single

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipeItem(project: RecipeItem)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchWord(project: SearchItem)

    @Query("SELECT * FROM searchitem")
    fun getAllSearchItem(): Single<List<SearchItem>>

    @Query("SELECT * FROM recipeitem")
    fun getAllRecipeItem(): List<RecipeItem>

    @Query("DELETE FROM recipeitem")
    fun nukeTableRecipeItem()
}