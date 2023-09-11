package com.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CacheEntity::class], version = 5, exportSchema = false)
@TypeConverters(TypeCache::class)
abstract class CacheDataBase :RoomDatabase(){
    abstract fun dao():CacheDao
}