package com.example.historyapp.ui.fragment.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.HistoryModel
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    fun getHistoryById(id: Int): LiveData<HistoryModel> = repository.getHistoryById(id)

    fun getAllHistory(): LiveData<List<HistoryModel>> = repository.getAllHistory  // Add this method

    fun insertHistory(historyModel: HistoryModel) {
        viewModelScope.launch {
            repository.insert(historyModel)
        }
    }

    fun updateHistory(historyModel: HistoryModel) {
        viewModelScope.launch {
            repository.update(historyModel)
        }
    }
}
