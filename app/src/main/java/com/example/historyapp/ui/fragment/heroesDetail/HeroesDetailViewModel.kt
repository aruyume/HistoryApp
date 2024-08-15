package com.example.historyapp.ui.fragment.heroesDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.historyapp.data.model.HeroModel
import com.example.historyapp.data.repositories.HeroRepository
import kotlinx.coroutines.launch

class HeroesDetailViewModel(private val repository: HeroRepository) : ViewModel() {

    private val _heroesId = MutableLiveData<Int>()
    val heroes: LiveData<HeroModel> = _heroesId.switchMap { id ->
        repository.getHeroesById(id)
    }

    fun loadHistory(id: Int) {
        _heroesId.value = id
    }

    fun saveHistory(heroModel: HeroModel) {
        viewModelScope.launch {
            if (heroModel.id == 0) {
                repository.insert(heroModel)
            } else {
                repository.update(heroModel)
            }
        }
    }
}