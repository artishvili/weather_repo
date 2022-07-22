package com.devshish.weather.di.module

import com.devshish.weather.di.component.AppScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
internal class NavigationModule {

    @[Provides AppScope]
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @[Provides AppScope]
    fun provideRouter(cicerone: Cicerone<Router>): Router {
        return cicerone.router
    }

    @[Provides AppScope]
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}
