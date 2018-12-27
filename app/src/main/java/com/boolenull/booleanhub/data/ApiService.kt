package com.boolenull.booleanhub.data

import com.boolenull.booleanhub.model.RepositoryModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users/booleanull/repos")
    fun getRepositories(): io.reactivex.Observable<List<RepositoryModel>>
}
