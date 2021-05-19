package com.carbit3333333.giphyapp.di.modules

import androidx.lifecycle.ViewModel
import com.carbit3333333.giphyapp.domain.GiphyViewModel
import com.carbit3333333.giphyapp.domain.MyViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GiphyViewModelModule {
    @Binds
    @IntoMap
    @MyViewModelKey(GiphyViewModel::class)
    abstract fun bindUserViewModel(giphyViewModel: GiphyViewModel): ViewModel
}