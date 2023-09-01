package com.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.database.MovieEntity
import com.data.repository.RepositoryFavorite
import com.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFavorite @Inject constructor(private val repository:RepositoryFavorite):ViewModel(){

    val movieData=MutableLiveData<DataStatus<List<MovieEntity>>>()

    fun loadMovies()=viewModelScope.launch {
        repository.getAllMovie().collect{
            movieData.postValue(DataStatus.success(it,it.isEmpty()))
        }
    }
}