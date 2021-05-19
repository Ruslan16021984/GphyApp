package com.carbit3333333.giphyapp.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.carbit3333333.giphyapp.entity.GiphyEntity
import io.reactivex.Completable

@Dao
interface GiphyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGiphyUrl(giphyEntity: GiphyEntity): Completable

}