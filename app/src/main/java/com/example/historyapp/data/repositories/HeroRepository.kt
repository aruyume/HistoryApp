package com.example.historyapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.historyapp.data.db.daos.HeroDao
import com.example.historyapp.data.model.HeroModel

class HeroRepository(private val heroDao: HeroDao) {

    val getAllHeroes: LiveData<List<HeroModel>> = heroDao.getAll()

    suspend fun clearData() =
        heroDao.clearAll()

    suspend fun insert(heroModel: HeroModel) =
        heroDao.insert(heroModel)

    fun getHeroesById(id: Int): LiveData<HeroModel> =
        heroDao.getById(id)

    suspend fun update(heroModel: HeroModel) =
        heroDao.update(heroModel)
}