package com.carbit3333333.giphyapp.repository.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carbit3333333.giphyapp.entity.Giphy
import com.carbit3333333.giphyapp.repository.NetworkState
import com.carbit3333333.giphyapp.repository.api.ApiService
import io.reactivex.schedulers.Schedulers

class GiftSingleNetworkDataSource(private val apiService: ApiService) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloaderGiftDetailsResponse = MutableLiveData<Giphy>()
    val downloaderGiftDetailsResponse: LiveData<Giphy>
        get() = _downloaderGiftDetailsResponse

    @SuppressLint("CheckResult")
    fun fethMovieDetails(giftId: String){
        _networkState.postValue(NetworkState.LOADING)

        try {

            apiService.getGiftDetails(giftId).subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e("getGiftDetails", it.toString())
                    _downloaderGiftDetailsResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                },{
                    _networkState.postValue(NetworkState.ERORR)
                })
        }catch (e: Exception){
        }
    }
}