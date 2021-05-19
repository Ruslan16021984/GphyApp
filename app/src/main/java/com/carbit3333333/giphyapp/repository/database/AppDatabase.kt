package com.carbit3333333.giphyapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carbit3333333.giphyapp.entity.GiphyEntity

@Database(
    entities = [GiphyEntity::class], version = 1, exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun  giphyDao(): GiphyDao
}