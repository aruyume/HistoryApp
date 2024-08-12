package com.example.historyapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.data.db.daos.HistoryDao
import com.example.historyapp.data.repositories.BaseRepository

class HistoryRepository(private val historyDao: HistoryDao) : BaseRepository<HistoryModel>() {
    override val getAllData: LiveData<List<HistoryModel>> = historyDao.getAll()
    override suspend fun clearData() = historyDao.clearAll()

    suspend fun insert(historyModel: HistoryModel) = historyDao.insert(historyModel)
    fun getHistoryById(id: Int): LiveData<HistoryModel> = historyDao.getById(id)
    suspend fun update(historyModel: HistoryModel) = historyDao.update(historyModel)
}
