package com.receipe.di.components

import com.receipe.di.annotations.SearchRecipeScope
import com.receipe.di.modules.search_frg.SearchViewModelModule
import com.recipes.fragments.search_recipe.SearchRecipeFragment
import com.recipes.fragments.search_recipe.SearchViewModel
import dagger.Subcomponent

@SearchRecipeScope
@Subcomponent(modules = [SearchViewModelModule::class])
interface SearchRecipeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchRecipeComponent
    }

    fun inject(searchViewModel: SearchViewModel)
    fun inject(searchRecipeFragment: SearchRecipeFragment)
}