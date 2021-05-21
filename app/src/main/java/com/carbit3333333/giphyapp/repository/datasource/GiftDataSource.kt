package com.carbit3333333.giphyapp.repository.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.carbit3333333.giphyapp.entity.GiphyEntity
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.NetworkState
import com.carbit3333333.giphyapp.repository.api.ApiService
import com.carbit3333333.giphyapp.repository.api.ApiService.Companion.LIMIT
import com.carbit3333333.giphyapp.repository.api.ApiService.Companion.OFFSET
import com.carbit3333333.giphyapp.repository.database.AppDatabase
import com.carbit3333333.giphyapp.repository.database.GiphyDao
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class GiftDataSource(private val apiService: ApiService,
                     private val appDatabase: AppDatabase,
                     private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, SingleGiphy>() {
    private var limit = LIMIT
    private var offset = OFFSET
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SingleGiphy>
    ) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add( apiService.getTrendingGiphy(limit, offset ).subscribeOn(Schedulers.io()).map {
               it.giphyList.map {  appDatabase.giphyDao().addGiphy(GiphyEntity(it.id, it.username, it.images.original.url))}
                it }.subscribe({
                callback.onResult(
                    it.giphyList, null, offset + 20
                )
                networkState.postValue(NetworkState.LOADED)
            }, {
                networkState.postValue(NetworkState.ERORR)
                Log.d("GiftDataSource", it.message.toString())
            }))
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SingleGiphy>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add( apiService.getTrendingGiphy( limit, params.key)
                .subscribeOn(Schedulers.io()).subscribe({
                    Log.e("loadAfter_totalCount", it.giphyList.toString())
                    if (it.pagination.totalCount >= params.key){
                        Log.e("loadAfter params", params.key.toString())
                        callback.onResult(it.giphyList, params.key+20)
                        networkState.postValue(NetworkState.LOADED)
                    }else{
                        networkState.postValue(NetworkState.ENDOFLIST)
                    }
                }, {
                    networkState.postValue(NetworkState.ERORR)
                    Log.e("GiftDataSource", it.message.toString())
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SingleGiphy>) {
    }
}