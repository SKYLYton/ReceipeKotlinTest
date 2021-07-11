package com.receipe.di.modules

import android.content.Context
import androidx.room.Room
import com.receipe.room.model.Database
import com.receipe.room.model.RecipeDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule() {

    @Provides
    @Singleton
    @Named("database_name")
    fun provideDatabaseName(): String {
        return "Recipe_database"
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context, @Named("database_name") name: String): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            name
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: Database): RecipeDao {
        return database.getRecipeDao()
    }

}