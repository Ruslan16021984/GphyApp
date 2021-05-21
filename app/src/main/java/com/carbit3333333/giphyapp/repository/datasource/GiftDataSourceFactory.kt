package com.carbit3333333.giphyapp.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.api.ApiService
import com.carbit3333333.giphyapp.repository.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable

class GiftDataSourceFactory(private val apiService: ApiService,
                            private val appDatabase: AppDatabase,
                            private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, SingleGiphy>(){
    private var searchText: String? = null
    val moviesLiveDataSource = MutableLiveData<GiftDataSource>()
    override fun create(): DataSource<Int, SingleGiphy> {
        val giftDataSource =
            GiftDataSource(
                apiService, appDatabase, compositeDisposable
            )
        moviesLiveDataSource.postValue(giftDataSource)
        return giftDataSource
    }


}