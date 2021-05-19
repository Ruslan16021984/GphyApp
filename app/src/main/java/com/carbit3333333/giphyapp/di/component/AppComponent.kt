package com.carbit3333333.giphyapp.di.component

import android.app.Application
import com.carbit3333333.giphyapp.App
import com.carbit3333333.giphyapp.di.modules.ActivityBuildersModule
import com.carbit3333333.giphyapp.di.modules.DbModule
import com.carbit3333333.giphyapp.di.modules.ViewModuleFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
   modules = [ AndroidInjectionModule::class,
    DbModule::class,
    ActivityBuildersModule::class,
    ViewModuleFactoryModule::class])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
