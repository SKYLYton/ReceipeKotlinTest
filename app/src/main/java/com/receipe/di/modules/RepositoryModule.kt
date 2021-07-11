package com.receipe.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.receipe.fragments.search_recipe.LoaderApi
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
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named("recipe_api_url")
    fun provideURL(): String {
        return "https://api.edamam.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(@Named("recipe_api_url") url: String): Retrofit {
/*
        val rxAdapter = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())
*/
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
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
        dao: RecipeDao
    ): LoaderApi {
        return LoaderApi(apiRequestWorker, mapper, dao)
    }

}