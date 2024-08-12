package com.example.historyapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.historyapp.data.db.daos.BaseDao

abstract class BaseRepository<T> {
    abstract val getAllData: LiveData<List<T>>
    abstract suspend fun clearData()
}

