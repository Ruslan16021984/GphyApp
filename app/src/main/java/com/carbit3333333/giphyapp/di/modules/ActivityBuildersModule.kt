package com.carbit3333333.giphyapp.di.modules

import com.carbit3333333.giphyapp.presentation.allGift.MainListGifActivity
import com.carbit3333333.giphyapp.presentation.detailGift.GiftDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [ApiModule::class, GiphyViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainListGifActivity

    @ContributesAndroidInjector(
        modules = [ApiModule::class, GiphyViewModelModule::class]
    )
    abstract fun contributeDetailActivity(): GiftDetailActivity
}