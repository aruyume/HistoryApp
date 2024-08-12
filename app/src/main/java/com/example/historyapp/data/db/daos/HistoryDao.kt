package com.example.historyapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.historyapp.data.model.HistoryModel

@Dao
interface HistoryDao : BaseDao<HistoryModel> {

    @Insert
    suspend fun insert(item: HistoryModel)

    @Query("SELECT * FROM history_table WHERE id = :id")
    fun getById(id: Int): LiveData<HistoryModel>

    @Update
    suspend fun update(item: HistoryModel)
}