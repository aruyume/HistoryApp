package com.example.historyapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.historyapp.data.model.HistoryModel

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<HistoryModel>>

    @Insert
    suspend fun insert(history: HistoryModel)

    @Update
    suspend fun update(history: HistoryModel)

    @Query("DELETE FROM history_table")
    suspend fun clearAll()

    @Query("SELECT * FROM history_table WHERE id = :id")
    fun getById(id: Int): LiveData<HistoryModel>
}