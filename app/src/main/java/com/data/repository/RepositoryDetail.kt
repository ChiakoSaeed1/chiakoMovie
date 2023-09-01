package com.data.repository

import androidx.lifecycle.MutableLiveData
import com.data.database.MovieDao
import com.data.database.MovieEntity
import com.data.service.ApiServices
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryDetail @Inject constructor(private val api:ApiServices,private val dao:MovieDao){
    suspend fun apiDetail(id:Int)=api.apiDetail(id)
    suspend fun saveMovie(entity: MovieEntity)=dao.saveMovie(entity)
    suspend fun deleteMovie(entity: MovieEntity)=dao.deleteMovie(entity)
     fun existMovie(id: Int)=dao.existMovie(id)

}