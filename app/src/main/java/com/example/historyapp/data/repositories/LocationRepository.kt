package com.example.historyapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.historyapp.data.db.daos.LocationDao
import com.example.historyapp.data.model.LocationModel

class LocationRepository(private val locationDao: LocationDao) {

    val getAllLocations: LiveData<List<LocationModel>> = locationDao.getAll()

    suspend fun clearData() =
        locationDao.clearAll()

    suspend fun insert(locationModel: LocationModel) =
        locationDao.insert(locationModel)

    fun getLocationsById(id: Int): LiveData<LocationModel> =
        locationDao.getById(id)

    suspend fun update(locationModel: LocationModel) =
        locationDao.update(locationModel)
}