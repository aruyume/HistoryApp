package com.example.historyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class LocationModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val location: String
)