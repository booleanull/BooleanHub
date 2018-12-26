package com.boolenull.booleanhub.data

import com.boolenull.booleanhub.model.RepositoryModel

class ApiDataManager(val apiService: ApiService) {

    fun getRepositories(): io.reactivex.Observable<List<RepositoryModel>> {
        return apiService.getRepositories()
    }
}
