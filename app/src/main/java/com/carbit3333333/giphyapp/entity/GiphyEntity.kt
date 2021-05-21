package com.carbit3333333.giphyapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GiphyEntity(
        @PrimaryKey
        var id: String,
        var username: String,
        var url: String
)