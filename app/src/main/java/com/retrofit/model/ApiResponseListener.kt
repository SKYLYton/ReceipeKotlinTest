package com.recipes.retrofit.model

import retrofit2.Call
import retrofit2.Response

interface ApiResponseListener<T> {
    fun onSuccess(call: Call<T>?, response: Response<T>?)
    fun onError(call: Call<T>?, response: Response<T>?)
    fun onFailure(call: Call<T>?, throwable: Throwable?)
}