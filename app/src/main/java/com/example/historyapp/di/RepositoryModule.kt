package com.example.historyapp.di

import com.example.historyapp.data.repositories.HistoryRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    factory {
        HistoryRepository(get())
    }
}