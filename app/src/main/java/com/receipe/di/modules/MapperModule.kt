package com.receipe.di.modules

import com.receipe.fragments.history.HistoryModelMapper
import com.receipe.fragments.search_recipe.SearchModelMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideSearchModelMapper(): SearchModelMapper {
        return SearchModelMapper()
    }

    @Provides
    @Singleton
    fun provideHistoryModelMapper(): HistoryModelMapper {
        return HistoryModelMapper()
    }
}