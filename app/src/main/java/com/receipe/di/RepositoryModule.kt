package com.recipes.di

import com.recipes.fragments.search_recipe.SearchRestRepositoryImpl
import com.recipes.retrofit.model.ApiRequest
import com.recipes.retrofit.model.ApiRequestImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(): ApiRequestImpl {
        return ApiRequestImpl()
    }

    @Provides
    fun provideSearchRepository(apiRequestImpl: ApiRequestImpl): SearchRestRepositoryImpl {
        return SearchRestRepositoryImpl(apiRequestImpl)
    }
}