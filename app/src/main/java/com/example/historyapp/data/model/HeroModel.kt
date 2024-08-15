package com.example.historyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_table")
data class HeroModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String
)