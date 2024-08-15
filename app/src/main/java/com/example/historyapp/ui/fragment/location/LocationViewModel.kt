package com.example.historyapp.ui.fragment.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.LocationModel
import com.example.historyapp.data.repositories.LocationRepository
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: LocationRepository) : ViewModel() {

    val allData: LiveData<List<LocationModel>> = repository.getAllLocations

    fun clearData() {
        viewModelScope.launch {
            repository.clearData()
        }
    }
}