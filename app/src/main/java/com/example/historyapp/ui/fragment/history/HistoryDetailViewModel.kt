package com.example.historyapp.ui.fragment.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.HistoryModel
import kotlinx.coroutines.launch
class HistoryDetailViewModel(private val repository: HistoryRepository) : ViewModel() {
    private val _history = MutableLiveData<HistoryModel>()
    val history: LiveData<HistoryModel> get() = _history

    fun loadHistory(id: Int) {
        viewModelScope.launch {
            _history.value = repository.getHistoryById(id).value // Update to handle LiveData properly
        }
    }

    fun saveHistory(historyModel: HistoryModel) {
        viewModelScope.launch {
            if (historyModel.id == -1) {
                repository.insert(historyModel)
            } else {
                repository.update(historyModel)
            }
        }
    }
}
