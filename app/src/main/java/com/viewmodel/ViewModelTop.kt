package com.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.cache.CacheEntity
import com.data.models.ResponseGenres
import com.data.models.ResponseTopTv
import com.data.repository.RepositoryTop
import com.utils.BaseResponse
import com.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelTop @Inject constructor(private val repository:RepositoryTop):ViewModel(){

    val topData=MutableLiveData<MyResponse<ResponseTopTv>>()
    val popData=MutableLiveData<MyResponse<ResponseTopTv>>()
    val recData=MutableLiveData<MyResponse<ResponseTopTv>>()
    val apiLast=MutableLiveData<MyResponse<ResponseTopTv>>()




    fun loadTopData(id:Int)=viewModelScope.launch {
        topData.postValue(MyResponse.LOADING())
        val response=repository.apiTop(id)
        topData.postValue(BaseResponse(response).generateApi())

        val cache=topData.value?.data
        if (cache != null) {
            offlineCache(cache)
        }
    }
    fun loadPopData(id:Int)=viewModelScope.launch {
        popData.postValue(MyResponse.LOADING())
        val response=repository.apiPop(id)
        popData.postValue(BaseResponse(response).generateApi())
    }
    fun loadRecData(id:Int)=viewModelScope.launch {
        recData.postValue(MyResponse.LOADING())
        val response=repository.apiRec(id)
        recData.postValue(BaseResponse(response).generateApi())
    }
    fun loadLastData(id:Int)=viewModelScope.launch {
        apiLast.postValue(MyResponse.LOADING())
        val response=repository.apiLast(id)
        apiLast.postValue(BaseResponse(response).generateApi())
    }

    val apiGenres=MutableLiveData<MyResponse<ResponseGenres>>()

    fun loadGenres()=viewModelScope.launch{
        apiGenres.postValue(MyResponse.LOADING())
        val response=repository.apiGenres()
        apiGenres.postValue(BaseResponse(response).generateApi())

    }

    //cache
    private fun saveCache(entity: CacheEntity)=viewModelScope.launch {
        repository.saveCache(entity)
    }
    val readCache=repository.readCache

    private fun offlineCache(response:ResponseTopTv){
        val entity=CacheEntity(0,response)
        saveCache(entity)
    }

}