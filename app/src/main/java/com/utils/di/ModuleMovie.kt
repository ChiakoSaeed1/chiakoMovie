package com.utils.di

import com.data.service.ApiServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.utils.BASE_URL

import com.utils.Time
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleMovie {

    @Provides
    @Singleton
    fun provideTime()= Time

    @Provides
    @Singleton
    fun provideBaseUrl()= BASE_URL

    @Provides
    @Singleton
    fun provideGson():Gson=GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideInterceptor()= HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideClient(time:Long,interceptor: HttpLoggingInterceptor)= OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .writeTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .connectTimeout(time, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient, baseUrl:String): ApiServices =
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)

}