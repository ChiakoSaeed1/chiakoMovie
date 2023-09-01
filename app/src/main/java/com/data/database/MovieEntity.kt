package com.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieEntity (
    @PrimaryKey
    var id:Int=0,
    var image:String="",
    var title:String=""
)

