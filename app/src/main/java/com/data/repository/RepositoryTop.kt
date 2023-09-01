package com.data.repository

import com.data.service.ApiServices
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryTop @Inject constructor(private val api:ApiServices) {
    suspend fun apiTop(id:Int)=api.apiTop(id)
    suspend fun apiGenres()=api.apiGenres()
    suspend fun apiPop(id:Int)=api.apiPop(id)
    suspend fun apiRec(id:Int)=api.apiRec(id)
    suspend fun apiLast(id:Int)=api.apiLast(id)


}