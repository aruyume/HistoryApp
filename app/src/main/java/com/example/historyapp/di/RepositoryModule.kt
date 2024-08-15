package com.example.historyapp.di

import com.example.historyapp.data.repositories.HeroRepository
import com.example.historyapp.data.repositories.HistoryRepository
import com.example.historyapp.data.repositories.LocationRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    factory {
        HeroRepository(get())
    }
    factory {
        HistoryRepository(get())
    }
    factory {
        LocationRepository(get())
    }
}