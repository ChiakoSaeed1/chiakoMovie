package com.utils

data class DataStatus<T>(val status:Status,val data:T?=null,val isEmpty:Boolean?=null){

    enum class Status{
        SUCCESS
    }

    companion object{
    fun<T> success(data: T?,isEmpty: Boolean?):DataStatus<T>{
        return DataStatus(Status.SUCCESS,data,isEmpty)
    }
        }
}

