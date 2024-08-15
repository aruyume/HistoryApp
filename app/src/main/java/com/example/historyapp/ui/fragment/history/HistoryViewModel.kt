package com.example.historyapp.ui.fragment.history

import com.example.historyapp.data.model.HistoryModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.repositories.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    val allData: LiveData<List<HistoryModel>> = repository.getAllHistory

    fun clearData() {
        viewModelScope.launch {
            repository.clearData()
        }
    }
}