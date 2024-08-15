package com.example.historyapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.historyapp.data.model.LocationModel

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table")
    fun getAll(): LiveData<List<LocationModel>>

    @Insert
    suspend fun insert(location: LocationModel)

    @Update
    suspend fun update(location: LocationModel)

    @Query("DELETE FROM location_table")
    suspend fun clearAll()

    @Query("SELECT * FROM location_table WHERE id = :id")
    fun getById(id: Int): LiveData<LocationModel>
}