package com.carbit3333333.giphyapp.repository.api

import com.carbit3333333.giphyapp.entity.Giphy
import com.carbit3333333.giphyapp.entity.GiphyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val LIMIT = 20
        const val OFFSET = 0
        const val G = ""
    }

    @GET("gifs/{gift_id}")
    fun getGiftDetails(@Path("gift_id") id: String): Single<Giphy>

    @GET("gifs/trending")
    fun getTrendingGiphy(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<GiphyResponse>
    @GET("gifs/search")
    fun searchGiff( @Query("q") q: String
    ): Single<GiphyResponse>

}
//    https://api.giphy.com/v1/gifs/L4N4GH5U75EtdZHeOp?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL
//    https://api.giphy.com/v1/gifs/trending?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&limit=25&offset=0&rating=g