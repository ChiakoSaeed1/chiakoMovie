package com.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.models.ResponseTopTv
import com.utils.CACHE_TABLE_ENTITY

@Entity(CACHE_TABLE_ENTITY)
data class CacheEntity(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var response:ResponseTopTv
)
