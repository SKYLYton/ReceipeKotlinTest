package com.receipe

import android.app.Application
import com.receipe.di.components.ApplicationComponent
import com.receipe.di.components.DaggerApplicationComponent
import com.receipe.di.modules.AppModule

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        initDagger()
    }

    companion object {
        lateinit var instance: App
    }

    private fun initDagger() {
        //applicationComponent.getMainComponentFactory().create()


        applicationComponent =
            DaggerApplicationComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()

        //mainComponent = applicationComponent.getMainComponentFactory().create()
    }
}