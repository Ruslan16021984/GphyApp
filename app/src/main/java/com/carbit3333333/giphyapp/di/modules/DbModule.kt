package com.carbit3333333.giphyapp.di.modules

import android.app.Application
import androidx.room.Room
import com.carbit3333333.giphyapp.repository.database.AppDatabase
import com.carbit3333333.giphyapp.repository.database.GiphyDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }
    @Singleton
    @Provides
    fun provideAuthTokenDao(db: AppDatabase): GiphyDao {
        return db.giphyDao()
    }
}