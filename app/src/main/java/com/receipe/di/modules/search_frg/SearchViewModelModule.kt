package com.receipe.di.modules.search_frg

import androidx.lifecycle.ViewModel
import com.receipe.di.annotations.SearchRecipeScope
import com.receipe.di.annotations.ViewModelKey
import com.recipes.fragments.search_recipe.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @SearchRecipeScope
    abstract fun bindViewModel(viewModelModule: SearchViewModel): ViewModel

/*    @Binds
    @MainScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory*/

}