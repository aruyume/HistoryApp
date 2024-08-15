package com.example.historyapp.ui.fragment.locationDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.LocationModel
import com.example.historyapp.data.repositories.LocationRepository
import kotlinx.coroutines.launch

class LocationDetailViewModel(private val repository: LocationRepository) : ViewModel() {

    private val _locationId = MutableLiveData<Int>()
    val location: LiveData<LocationModel> = _locationId.switchMap { id ->
        repository.getLocationsById(id)
    }

    fun loadLocation(id: Int) {
        _locationId.value = id
    }

    fun saveLocation(locationModel: LocationModel) {
        viewModelScope.launch {
            if (locationModel.id == 0) {
                repository.insert(locationModel)
            } else {
                repository.update(locationModel)
            }
        }
    }
}