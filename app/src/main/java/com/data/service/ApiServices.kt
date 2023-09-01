package com.data.service

import com.data.models.ResponseDetail
import com.data.models.ResponseGenres
import com.data.models.ResponseTopTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("genres/{genre_id}/movies")
    suspend fun apiTop(@Path("genre_id") id:Int ):Response<ResponseTopTv>

    @GET("genres")
    suspend fun apiGenres():Response<ResponseGenres>

    @GET("genres/{genre_id}/movies")
    suspend fun apiPop(@Path("genre_id") id:Int ):Response<ResponseTopTv>

    @GET("genres/{genre_id}/movies")
    suspend fun apiRec(@Path("genre_id") id:Int ):Response<ResponseTopTv>

    @GET("genres/{genre_id}/movies")
    suspend fun apiLast(@Path("genre_id") id:Int ):Response<ResponseTopTv>

    @GET("movies/{movie_id}")
    suspend fun apiDetail(@Path("movie_id") id: Int):Response<ResponseDetail>

    @GET("movies")
    suspend fun apiSearch(@Query("q") search:String):Response<ResponseTopTv>

}