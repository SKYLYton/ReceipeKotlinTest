package com.recipes.di

import com.recipes.fragments.search_recipe.SearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {
    fun inject(searchViewModel: SearchViewModel)
}