package com.receipe.fragments.recipe_item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {
    fun getLiveData() = liveData
}