package com.boolenull.booleanhub.di

import android.arch.persistence.room.Room
import android.content.Context
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.data.RepositoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(context: Context): RepositoryDatabase {
        return Room.databaseBuilder(context, RepositoryDatabase::class.java, "repository")
            .build()
    }
}