package com.carbit3333333.giphyapp.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.api.ApiService

class GiftSearchDataSourceFactory(private val apiService: ApiService,private val search: String) : DataSource.Factory<Int, SingleGiphy>(){
    val moviesLiveDataSource = MutableLiveData<GiftDataSource>()
    override fun create(): DataSource<Int, SingleGiphy> {
        val giftDataSource =
            GiftSearchDataSource(
                apiService, search
            )
//        moviesLiveDataSource.postValue(giftDataSource)
        return giftDataSource
    }

}