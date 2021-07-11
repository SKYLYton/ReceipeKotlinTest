package com.receipe.di.components

import com.receipe.di.modules.AppModule
import com.receipe.di.modules.DatabaseModule
import com.receipe.di.modules.MapperModule
import com.receipe.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, MapperModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun getSearchRecipeComponentFactory(): SearchRecipeComponent.Factory
    fun getHistoryComponentFactory(): HistoryComponent.Factory
}