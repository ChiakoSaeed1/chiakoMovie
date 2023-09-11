package com.data.repository

import androidx.lifecycle.asLiveData
import com.data.cache.CacheDao
import com.data.cache.CacheEntity
import com.data.service.ApiServices
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryTop @Inject constructor(private val api:ApiServices,private val dao: CacheDao) {
    suspend fun apiTop(id:Int)=api.apiTop(id)
    suspend fun apiGenres()=api.apiGenres()
    suspend fun apiPop(id:Int)=api.apiPop(id)
    suspend fun apiRec(id:Int)=api.apiRec(id)
    suspend fun apiLast(id:Int)=api.apiLast(id)

    //local
    suspend fun saveCache(entity: CacheEntity)=dao.saveCache(entity)
    val readCache=dao.getAllCache().asLiveData()



}