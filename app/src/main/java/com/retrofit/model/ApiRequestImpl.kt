package com.recipes.retrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiRequestImpl @Inject constructor () {
    var _apiRequest: ApiRequest

    val apiRequest: ApiRequest
    get() = _apiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiRequest.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        _apiRequest = retrofit.create(ApiRequest::class.java)
    }
}