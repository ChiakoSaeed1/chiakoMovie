package com.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.data.models.ResponseTopTv
import com.utils.CACHE_TABLE_ENTITY
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveCache(cacheEntity: CacheEntity)

    @Query("SELECT * FROM $CACHE_TABLE_ENTITY")
    fun getAllCache():Flow<List<CacheEntity>>
}