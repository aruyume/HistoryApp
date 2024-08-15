package com.example.historyapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.data.db.daos.HistoryDao

class HistoryRepository(private val historyDao: HistoryDao) {

    val getAllHistory: LiveData<List<HistoryModel>> = historyDao.getAll()

    suspend fun clearData() =
        historyDao.clearAll()

    suspend fun insert(historyModel: HistoryModel) =
        historyDao.insert(historyModel)

    fun getHistoryById(id: Int): LiveData<HistoryModel> =
        historyDao.getById(id)

    suspend fun update(historyModel: HistoryModel) =
        historyDao.update(historyModel)
}