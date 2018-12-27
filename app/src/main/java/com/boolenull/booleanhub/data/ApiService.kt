package com.boolenull.booleanhub.data

import com.boolenull.booleanhub.model.RepositoryModel
import retrofit2.http.GET

interface ApiService {

    @GET("users/booleanull/repos")
    fun getRepositories(): io.reactivex.Observable<List<RepositoryModel>>
}
