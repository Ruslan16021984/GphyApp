package com.carbit3333333.giphyapp.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carbit3333333.giphyapp.entity.GiphyEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GiphyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGiphy(giphyEntity: GiphyEntity): Completable

    @Query("SELECT * FROM GiphyEntity")
    fun getAll(): Single<List<GiphyEntity>>

    @Query("SELECT * FROM GiphyEntity WHERE username = :username")
    fun getAllName(username: String): Single<List<GiphyEntity>>

}