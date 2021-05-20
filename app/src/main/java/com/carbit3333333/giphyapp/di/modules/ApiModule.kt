package com.carbit3333333.giphyapp.di.modules

import android.util.Log
import com.carbit3333333.giphyapp.BuildConfig
import com.carbit3333333.giphyapp.repository.AppRepository
import com.carbit3333333.giphyapp.repository.allGiphyRepo.GiftPagedListRepository
import com.carbit3333333.giphyapp.repository.allGiphyRepo.GiftSerachPagedListRepository
import com.carbit3333333.giphyapp.repository.api.ApiService
import com.carbit3333333.giphyapp.repository.api.ServerCommunicator
import com.carbit3333333.giphyapp.repository.database.AppDatabase
import com.carbit3333333.giphyapp.repository.singleGiftRepo.GiftDetailsRepository
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    companion object{
        private const val API_URL = "https://api.giphy.com/v1/"
        const val API_KEY = "kTjm4ewE9XpnlIHAfnZ0IGzXBYRlFRM4"
    }
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        val requestInterceptor = Interceptor{chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
        val builder = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return Retrofit.Builder().client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }
    @Provides
    fun provideRetrofit( builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(API_URL).build()
    }
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }

    @Provides
    fun provideCommunicator(apiService: ApiService): ServerCommunicator {
        return ServerCommunicator(apiService)
    }
    @Provides
    fun provideGiftPagedListRepository(apiService: ApiService): GiftPagedListRepository {
        return GiftPagedListRepository(apiService)
    }
    @Provides
    fun provideGiftDetailsRepository(apiService: ApiService): GiftDetailsRepository {
        return GiftDetailsRepository(apiService)
    }
    @Provides
    fun provideGiftSerachPagedListRepository(apiService: ApiService):GiftSerachPagedListRepository{
        return GiftSerachPagedListRepository(apiService)
    }

}