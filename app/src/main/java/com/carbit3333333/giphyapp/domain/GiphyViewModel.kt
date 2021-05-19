package com.carbit3333333.giphyapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.NetworkState
import com.carbit3333333.giphyapp.repository.allGiphyRepo.GiftPagedListRepository
import com.carbit3333333.giphyapp.repository.singleGiftRepo.GiftDetailsRepository
import com.carbit3333333.giphyapp.widjet.SingleLiveEvent
import javax.inject.Inject

class GiphyViewModel @Inject constructor(private val mGiftPagedListRepository: GiftPagedListRepository,
private val mGiftDetailsRepository: GiftDetailsRepository): ViewModel() {
    init {
        getPagedList()
    }
    val networkState: LiveData<NetworkState> by lazy {
    mGiftPagedListRepository.getNetworkState()
    }
    val moviePagedList : LiveData<PagedList<SingleGiphy>> by lazy {
        mGiftPagedListRepository.fetchLiveGiftPagedList()
    }
    fun listIsEmpty(): Boolean{
        return moviePagedList.value?.isEmpty()?:true
    }
    fun getPagedList():LiveData<PagedList<SingleGiphy>>{
       return mGiftPagedListRepository.fetchLiveGiftPagedList()
    }

}