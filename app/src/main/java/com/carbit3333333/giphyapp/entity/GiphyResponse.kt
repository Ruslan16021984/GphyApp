package com.carbit3333333.giphyapp.entity

import com.google.gson.annotations.SerializedName

data class GiphyResponse (
    @SerializedName("data")
    val giphyList: List<SingleGiphy>,
    @SerializedName("pagination")
    val pagination: Pagination
)