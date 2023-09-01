package com.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.database.MovieEntity
import com.data.models.ResponseDetail
import com.data.repository.RepositoryDetail
import com.utils.BaseResponse
import com.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetail @Inject constructor(private val repository:RepositoryDetail):ViewModel(){

    val detailData=MutableLiveData<MyResponse<ResponseDetail>>()

    fun loadDetail(id:Int)=viewModelScope.launch {
        detailData.postValue(MyResponse.LOADING())
        val response=repository.apiDetail(id)
        detailData.postValue(BaseResponse(response).generateApi())
    }

    fun saveMovie(entity: MovieEntity)=viewModelScope.launch {
        repository.saveMovie(entity)
    }
    fun deleteMovie(entity: MovieEntity)=viewModelScope.launch {
        repository.deleteMovie(entity)
    }

    val isFavorite=MutableLiveData<Boolean>()

    fun loadExist(id:Int)=viewModelScope.launch {
        repository.existMovie(id).collect{
            isFavorite.postValue(it)
        }
    }
}