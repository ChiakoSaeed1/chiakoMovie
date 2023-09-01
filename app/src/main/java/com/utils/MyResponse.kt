package com.utils

sealed class MyResponse<T>(val data:T?=null,val message:String?=null){
    class LOADING<T>:MyResponse<T>()
    class SUCCESS<T>(data: T?):MyResponse<T>(data)
    class ERROR<T>(message: String?):MyResponse<T>(message = message)
}
