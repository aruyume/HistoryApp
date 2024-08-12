package com.example.historyapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.historyapp.data.model.HistoryModel

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<HistoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyModel: HistoryModel)

    @Delete
    suspend fun deleteHistory(historyModel: HistoryModel)

    @Query("SELECT * FROM history_table WHERE id = :id")
    fun getHistoryById(id: Int): LiveData<HistoryModel>

    @Update
    suspend fun updateHistory(historyModel: HistoryModel)
}
