package com.utils

import retrofit2.Response

class BaseResponse<T>(private val response: Response<T>) {

    fun generateApi():MyResponse<T>{
        return when{
            response.code()==401->MyResponse.ERROR("401")
            response.code()==402->MyResponse.ERROR("402")
            response.code()==422->MyResponse.ERROR("422")
            response.code()==500->MyResponse.ERROR("500")
            response.isSuccessful->MyResponse.SUCCESS(response.body())
            else-> MyResponse.ERROR(response.message())
        }
    }
}