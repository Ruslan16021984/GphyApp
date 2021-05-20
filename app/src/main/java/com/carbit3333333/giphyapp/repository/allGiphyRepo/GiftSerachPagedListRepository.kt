package com.carbit3333333.giphyapp.repository.allGiphyRepo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.api.ApiService
import com.carbit3333333.giphyapp.repository.datasource.GiftSearchDataSourceFactory

class GiftSerachPagedListRepository(private val apiService: ApiService) {
    lateinit var giftPagedList: LiveData<PagedList<SingleGiphy>>
    lateinit var giftDataSourceFactory: GiftSearchDataSourceFactory

    fun fetchLiveGiftPagedList(search: String): LiveData<PagedList<SingleGiphy>> {
        giftDataSourceFactory =
            GiftSearchDataSourceFactory(
                apiService, search
            )


        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ApiService.LIMIT).build()

        giftPagedList = LivePagedListBuilder(giftDataSourceFactory, config).build()
        return giftPagedList
    }
//    fun getNetworkState(): LiveData<NetworkState> {
//        return Transformations.switchMap<GiftDataSource, NetworkState>(
//            giftDataSourceFactory.moviesLiveDataSource, GiftDataSource::networkState
//        )
//
//    }
}