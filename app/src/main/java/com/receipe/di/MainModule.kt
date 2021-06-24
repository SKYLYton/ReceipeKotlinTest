package com.receipe.di

import android.content.Context
import com.receipe.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule constructor(var context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = context
}