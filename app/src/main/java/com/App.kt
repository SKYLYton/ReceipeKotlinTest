package com

import android.app.Application
import com.recipes.di.AppComponent
import com.recipes.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        initDagger()
    }

    companion object {
        lateinit var instance: App
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().build()
    }
}