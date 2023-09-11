package com.utils.di

import android.content.Context
import androidx.room.Room
import com.data.cache.CacheDataBase
import com.utils.CACHE_TABLE_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDBCache {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context:Context)=Room.databaseBuilder(
        context,CacheDataBase::class.java, CACHE_TABLE_DB
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:CacheDataBase)=db.dao()

}