package com.recipes.di

import com.receipe.fragments.search_recipe.SearchLoaderImpl
import com.recipes.retrofit.model.ApiRequestImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(): ApiRequestImpl {
        return ApiRequestImpl()
    }

    @Provides
    fun provideSearchRepository(apiRequestImpl: ApiRequestImpl): SearchLoaderImpl {
        return SearchLoaderImpl(apiRequestImpl)
    }
}