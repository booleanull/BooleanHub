package com.boolenull.booleanhub.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.boolenull.booleanhub.ui.model.RepositoryModel


@Database(entities = arrayOf(RepositoryModel::class), version = 2, exportSchema = false)
abstract class RepositoryDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}