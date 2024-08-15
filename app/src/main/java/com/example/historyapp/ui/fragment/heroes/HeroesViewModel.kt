package com.example.historyapp.ui.fragment.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.HeroModel
import com.example.historyapp.data.repositories.HeroRepository
import kotlinx.coroutines.launch

class HeroesViewModel(private val repository: HeroRepository) : ViewModel() {

    val allData: LiveData<List<HeroModel>> = repository.getAllHeroes

    fun clearData() {
        viewModelScope.launch {
            repository.clearData()
        }
    }
}