package com.data.cache

import androidx.room.TypeConverter
import com.data.models.ResponseTopTv
import com.google.gson.Gson

class TypeCache {

    val gson =Gson()

    @TypeConverter
    fun convertToString(data:ResponseTopTv):String{
        return gson.toJson(data)
    }

    @TypeConverter
    fun convertToData(data:String):ResponseTopTv{
        return gson.fromJson(data,ResponseTopTv::class.java)
    }
}