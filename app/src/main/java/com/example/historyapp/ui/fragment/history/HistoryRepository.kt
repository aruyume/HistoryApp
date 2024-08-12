package com.example.historyapp.ui.fragment.history


import androidx.lifecycle.LiveData
import com.example.historyapp.data.db.daos.HistoryDao
import com.example.historyapp.data.model.HistoryModel

class HistoryRepository(private val historyDao: HistoryDao) {

    val getAllHistory: LiveData<List<HistoryModel>> = historyDao.getAll()

    suspend fun insert(historyModel: HistoryModel) {
        historyDao.insertHistory(historyModel)
    }


    fun getHistoryById(id: Int): LiveData<HistoryModel> {
        return historyDao.getHistoryById(id)
    }

    suspend fun update(historyModel: HistoryModel) {
        historyDao.updateHistory(historyModel)
    }
}
