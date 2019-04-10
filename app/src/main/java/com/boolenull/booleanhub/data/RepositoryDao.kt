package com.boolenull.booleanhub.data

import android.arch.persistence.room.*
import com.boolenull.booleanhub.ui.model.RepositoryModel


@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositoryModel ORDER BY id")
    fun all(): List<RepositoryModel>

    @Query("SELECT * FROM repositoryModel WHERE id = :id")
    fun getById(id: Long): RepositoryModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<RepositoryModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: RepositoryModel)

    @Delete
    fun delete(repositoryModel: RepositoryModel)

    @Query("DELETE FROM repositoryModel")
    fun deleteAll()
}