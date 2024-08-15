package com.example.historyapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.historyapp.data.model.HeroModel

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_table")
    fun getAll(): LiveData<List<HeroModel>>

    @Insert
    suspend fun insert(hero: HeroModel)

    @Update
    suspend fun update(hero: HeroModel)

    @Query("DELETE FROM hero_table")
    suspend fun clearAll()

    @Query("SELECT * FROM hero_table WHERE id = :id")
    fun getById(id: Int): LiveData<HeroModel>
}