package com.data.repository

import com.data.database.MovieDao
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryFavorite @Inject constructor(private val dao: MovieDao){
    fun getAllMovie()=dao.getAllMovie()
}