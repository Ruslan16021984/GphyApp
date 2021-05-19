package com.carbit3333333.giphyapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.carbit3333333.giphyapp.domain.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModuleFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}