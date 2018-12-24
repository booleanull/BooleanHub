package com.boolenull.booleanhub

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.boolenull.booleanhub.data.RepositoryDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, RepositoryDatabase::class.java, "repository")
            .build()
        context = this
    }

    companion object {
        lateinit var database: RepositoryDatabase
        lateinit var context: Context
    }
}