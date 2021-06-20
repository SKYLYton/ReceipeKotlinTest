package com.recipes.retrofit.model

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiRequestImpl @Inject constructor () {
    var _apiRequest: ApiRequest

    val apiRequest: ApiRequest
    get() = _apiRequest

    init {

        val rxAdapter = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiRequest.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build()
        _apiRequest = retrofit.create(ApiRequest::class.java)
    }
}