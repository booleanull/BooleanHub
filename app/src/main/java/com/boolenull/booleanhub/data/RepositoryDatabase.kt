package com.boolenull.booleanhub.data

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.boolenull.booleanhub.model.RepositoryModel


@Database(entities = arrayOf(RepositoryModel::class), version = 1)
abstract class RepositoryDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}