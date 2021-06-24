package com.recipes.fragments.history

sealed class AppState {
    data class Success<T>(val model: T) : AppState()
    data class Error(val code: Int?) : AppState()
    data class InvalidData(val resourceId: Int) : AppState()
    object Failure : AppState()
    object Loading : AppState()
}
