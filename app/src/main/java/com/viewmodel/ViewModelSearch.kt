package com.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.models.ResponseTopTv
import com.data.repository.RepositorySearch
import com.utils.BaseResponse
import com.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSearch @Inject constructor(private val repository:RepositorySearch):ViewModel(){
    val searchData=MutableLiveData<MyResponse<ResponseTopTv>>()

    fun loadSearch(search:String)=viewModelScope.launch {
        searchData.postValue(MyResponse.LOADING())
        val response=repository.apiSearch(search)
        searchData.postValue(BaseResponse(response).generateApi())
    }
}