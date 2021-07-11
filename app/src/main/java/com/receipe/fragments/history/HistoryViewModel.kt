package com.receipe.fragments.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.receipe.App
import com.receipe.BaseViewModel
import com.receipe.fragments.history.model.HistoryItem

import com.recipes.fragments.history.AppState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HistoryViewModel @Inject constructor(var loaderDatabase: LoaderDatabase) :
    BaseViewModel() {

    val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getHistory() {
        doWork {
            val result = loaderDatabase.getHistory()
            liveData.postValue(AppState.Success(result))
        }
    }

    override fun onStart() {
    }

}