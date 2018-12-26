package com.boolenull.booleanhub.data

object ApiProvide {

    fun provideApi(): ApiDataManager {
        return ApiDataManager(ApiService.create())
    }
}
