package com.example.historyapp.ui.fragment.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.repositories.BaseRepository
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(
    private val repository: BaseRepository<T>
) : ViewModel() {

    val allData: LiveData<List<T>> = repository.getAllData

    fun clearData() {
        viewModelScope.launch {
            repository.clearData()
        }
    }
}
