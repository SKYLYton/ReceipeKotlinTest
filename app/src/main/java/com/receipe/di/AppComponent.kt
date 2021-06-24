package com.recipes.di

import com.receipe.di.DatabaseModule
import com.receipe.di.MainModule
import com.receipe.di.MapperModule
import com.receipe.fragments.history.HistoryViewModel
import com.receipe.fragments.search_recipe.LoaderDatabase
import com.recipes.fragments.search_recipe.SearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, MapperModule::class, DatabaseModule::class, MainModule::class])
interface AppComponent {
    fun inject(searchViewModel: SearchViewModel)
    fun inject(historyViewModel: HistoryViewModel)
}