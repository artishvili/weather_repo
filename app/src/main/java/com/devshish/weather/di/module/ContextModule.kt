package com.devshish.weather.di.module

import android.app.Application
import android.content.Context
import com.devshish.weather.di.component.AppScope
import dagger.Binds
import dagger.Module

@Module
interface ContextModule {

    @[Binds AppScope]
    fun bindContext(application: Application): Context
}
