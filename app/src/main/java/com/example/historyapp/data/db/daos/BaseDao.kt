package com.example.historyapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BaseDao<T> {

    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<T>>

    @Query("DELETE FROM history_table")
    suspend fun clearAll()
}