package com.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.utils.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveMovie(entity: MovieEntity)

    @Delete
    suspend fun deleteMovie(entity: MovieEntity)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllMovie():Flow<List<MovieEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM ${TABLE_NAME} WHERE id=:id)")
    fun existMovie(id:Int):Flow<Boolean>
}