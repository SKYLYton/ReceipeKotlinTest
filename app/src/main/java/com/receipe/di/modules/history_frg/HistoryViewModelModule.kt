package com.receipe.di.modules.history_frg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.receipe.di.annotations.HistoryScope
import com.receipe.di.annotations.ViewModelKey
import com.receipe.fragments.history.HistoryViewModel
import com.recipes.fragments.search_recipe.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class HistoryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @HistoryScope
    abstract fun bindViewModel(viewModelModule: HistoryViewModel): ViewModel

/*    @Binds
    @MainScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory*/

}