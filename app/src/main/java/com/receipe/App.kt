package com.receipe

import android.app.Application
import com.receipe.di.DatabaseModule
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
        appComponent =
            DaggerAppComponent.builder().databaseModule(DatabaseModule(instance.applicationContext))
                .build()
    }
}