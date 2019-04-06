package com.boolenull.booleanhub.data

import com.boolenull.booleanhub.ui.model.RepositoryModel
import com.boolenull.booleanhub.utils.NICKNAME
import retrofit2.http.GET

interface ApiService {

    @GET("users/$NICKNAME/repos")
    fun getRepositories(): io.reactivex.Observable<List<RepositoryModel>>
}
