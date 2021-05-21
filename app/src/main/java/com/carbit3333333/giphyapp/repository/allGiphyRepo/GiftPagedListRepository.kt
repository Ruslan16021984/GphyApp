package com.carbit3333333.giphyapp.repository.allGiphyRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.datasource.GiftDataSource
import com.carbit3333333.giphyapp.repository.datasource.GiftDataSourceFactory
import com.carbit3333333.giphyapp.repository.NetworkState
import com.carbit3333333.giphyapp.repository.api.ApiService
import com.carbit3333333.giphyapp.repository.api.ApiService.Companion.LIMIT
import com.carbit3333333.giphyapp.repository.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable

class GiftPagedListRepository(private val apiService: ApiService,private val appDatabase: AppDatabase) {
    lateinit var giftPagedList: LiveData<PagedList<SingleGiphy>>
    lateinit var giftDataSourceFactory: GiftDataSourceFactory

    fun fetchLiveGiftPagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<SingleGiphy>>{
        giftDataSourceFactory =
            GiftDataSourceFactory(
                apiService, appDatabase, compositeDisposable
            )


        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT).build()

        giftPagedList = LivePagedListBuilder(giftDataSourceFactory, config).build()
        return giftPagedList
    }
    fun getNetworkState(): LiveData<NetworkState>{
        return Transformations.switchMap<GiftDataSource, NetworkState>(
            giftDataSourceFactory.moviesLiveDataSource, GiftDataSource::networkState
        )

    }

}