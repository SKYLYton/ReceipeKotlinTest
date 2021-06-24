package com.recipes.di

import com.receipe.fragments.search_recipe.LoaderApi
import com.receipe.fragments.search_recipe.LoaderDatabase
import com.receipe.fragments.search_recipe.SearchModelMapper
import com.receipe.retrofit.converter.ResponseConverter
import com.receipe.retrofit.model.ApiRequestWorker
import com.receipe.retrofit.model.ApiRequestWorkerImpl
import com.receipe.room.model.RecipeDao
import com.recipes.retrofit.model.ApiRequest
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val rxAdapter = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())

        return Retrofit.Builder()
            .baseUrl(ApiRequest.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiRequestWorker(
        retrofit: Retrofit,
        responseConverter: ResponseConverter
    ): ApiRequestWorker {
        val apiRequest = retrofit.create(ApiRequest::class.java)
        return ApiRequestWorkerImpl(apiRequest, responseConverter)
    }

    @Provides
    @Singleton
    fun provideResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Provides
    @Singleton
    fun provideLoaderApi(
        apiRequestWorker: ApiRequestWorker,
        mapper: SearchModelMapper,
        loaderDatabase: LoaderDatabase,
        dao: RecipeDao
    ): LoaderApi {
        return LoaderApi(apiRequestWorker, mapper, loaderDatabase, dao)
    }

}