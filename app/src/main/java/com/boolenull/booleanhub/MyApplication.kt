package com.boolenull.booleanhub

import android.app.Application
import com.boolenull.booleanhub.di.*

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        providerComponent = DaggerProviderComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule())
            .build()
    }

    companion object {
        lateinit var providerComponent: ProviderComponent
    }
}