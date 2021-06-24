package com.receipe.fragments.history

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.receipe.App
import com.receipe.fragments.history.model.HistoryItem

import com.recipes.fragments.history.AppState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HistoryViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    @Inject
    lateinit var loaderDatabase: LoaderDatabase

    init {
        App.instance.appComponent.inject(this)
    }

    fun getLiveData() = liveData

    fun getHistory() {
        loaderDatabase.getHistory().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<HistoryItem>>() {
                override fun onSuccess(t: List<HistoryItem>) {
                    liveData.value = AppState.Success(t)
                }

                override fun onError(e: Throwable) {
                    liveData.value = AppState.Error(0)
                }
            })
    }

}