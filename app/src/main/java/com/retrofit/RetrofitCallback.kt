package com.recipes.retrofit

import com.recipes.retrofit.model.ApiResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(
    private val mApiResponseListener: ApiResponseListener<T>?,
) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(call, response)
        } else {
            onError(call, response)
        }
    }

    private fun onSuccess(call: Call<T>?, response: Response<T>?) {
        mApiResponseListener?.onSuccess(call, response)
    }

    private fun onError(call: Call<T>?, response: Response<T>) {
        mApiResponseListener?.onError(call, response)
    }

    override fun onFailure(call: Call<T>, throwable: Throwable) {
        mApiResponseListener?.onFailure(call, throwable)
    }
}