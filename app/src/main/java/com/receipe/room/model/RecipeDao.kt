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
    suspend fun insertRecipeItem(project: RecipeItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeItems(projects: List<RecipeItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchWord(project: SearchItem)

    @Query("SELECT * FROM searchitem")
    suspend fun getAllSearchItem(): List<SearchItem>

    @Query("SELECT * FROM recipeitem")
    suspend fun getAllRecipeItem(): List<RecipeItem>

    @Query("DELETE FROM recipeitem")
    suspend fun nukeTableRecipeItem()
}