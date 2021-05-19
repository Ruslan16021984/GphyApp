package com.carbit3333333.giphyapp.entity


import com.google.gson.annotations.SerializedName

data class SingleGiphy(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    val url: String
)