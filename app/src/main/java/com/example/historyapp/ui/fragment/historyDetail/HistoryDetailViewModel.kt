package com.example.historyapp.ui.fragment.historyDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.data.repositories.HistoryRepository
import kotlinx.coroutines.launch

class HistoryDetailViewModel(private val repository: HistoryRepository) : ViewModel() {

    private val _historyId = MutableLiveData<Int>()
    val history: LiveData<HistoryModel> = _historyId.switchMap { id ->
        repository.getHistoryById(id)
    }

    fun loadHistory(id: Int) {
        _historyId.value = id
    }

    fun saveHistory(historyModel: HistoryModel) {
        viewModelScope.launch {
            if (historyModel.id == 0) {
                repository.insert(historyModel)
            } else {
                repository.update(historyModel)
            }
        }
    }
}