package com.carbit3333333.giphyapp.repository.singleGiftRepo

import androidx.lifecycle.LiveData
import com.carbit3333333.giphyapp.entity.Giphy
import com.carbit3333333.giphyapp.repository.datasource.GiftSingleNetworkDataSource
import com.carbit3333333.giphyapp.repository.NetworkState
import com.carbit3333333.giphyapp.repository.api.ApiService

class GiftDetailsRepository(private val apiService: ApiService) {
    lateinit var giftSingleNetworkDataSource: GiftSingleNetworkDataSource

    fun fethSingleGiftDetails(giftId: String): LiveData<Giphy> {
        giftSingleNetworkDataSource =
            GiftSingleNetworkDataSource(
                apiService
            )
        giftSingleNetworkDataSource.fethMovieDetails(giftId)
        return giftSingleNetworkDataSource.downloaderGiftDetailsResponse
    }
    fun getGiftDetailsNetworkState(): LiveData<NetworkState> {
        return giftSingleNetworkDataSource.networkState
    }
}