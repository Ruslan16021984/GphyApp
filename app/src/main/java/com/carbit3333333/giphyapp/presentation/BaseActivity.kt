package com.carbit3333333.giphyapp.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.carbit3333333.giphyapp.domain.GiphyViewModel
import com.carbit3333333.giphyapp.domain.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: GiphyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = this.run {
            ViewModelProvider(this, providerFactory).get(GiphyViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}