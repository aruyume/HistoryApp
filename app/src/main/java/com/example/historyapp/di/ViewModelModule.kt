package com.example.historyapp.di

import com.example.historyapp.ui.fragment.heroes.HeroesViewModel
import com.example.historyapp.ui.fragment.heroesDetail.HeroesDetailViewModel
import com.example.historyapp.ui.fragment.history.HistoryViewModel
import com.example.historyapp.ui.fragment.historyDetail.HistoryDetailViewModel
import com.example.historyapp.ui.fragment.location.LocationViewModel
import com.example.historyapp.ui.fragment.locationDetail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel {
        HistoryViewModel(get())
    }
    viewModel {
        HistoryDetailViewModel(get())
    }
    viewModel {
        HeroesViewModel(get())
    }
    viewModel {
        HeroesDetailViewModel(get())
    }
    viewModel {
        LocationViewModel(get())
    }
    viewModel {
        LocationDetailViewModel(get())
    }
}