package com.utils.di

import android.content.Context
import androidx.room.Room
import com.data.database.MovieDataBase
import com.data.database.MovieEntity
import com.utils.TABLE_NAME_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDb {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context)=Room.databaseBuilder(
        context,MovieDataBase::class.java, TABLE_NAME_DB
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:MovieDataBase)=db.dao()

    @Provides
    @Singleton
    fun provideEntity()=MovieEntity()
}