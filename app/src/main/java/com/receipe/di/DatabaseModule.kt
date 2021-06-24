package com.receipe.di

import android.content.Context
import androidx.room.Room
import com.receipe.fragments.search_recipe.LoaderDatabase
import com.receipe.room.model.Database
import com.receipe.room.model.RecipeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(var context: Context) {

    @Provides
    @Singleton
    fun provideDatabase(): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "Recipe_database"
        ).build()
    }

/*    @Provides
    @Singleton
    fun provideLoaderDatabase(database: Database): LoaderDatabase {
        return LoaderDatabase(database.getRecipeDao())
    }*/

    @Provides
    @Singleton
    fun provideDao(database: Database): RecipeDao {
        return database.getRecipeDao()
    }

}