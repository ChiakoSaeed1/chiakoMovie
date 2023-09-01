package com.data.repository

import com.data.service.ApiServices
import javax.inject.Inject

class RepositorySearch @Inject constructor(private val api:ApiServices) {
    suspend fun apiSearch(search:String)=api.apiSearch(search)
}