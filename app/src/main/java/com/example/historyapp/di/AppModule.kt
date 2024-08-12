package com.example.historyapp.di

import com.example.historyapp.data.db.AppDatabase
import com.example.historyapp.ui.fragment.history.HistoryDetailViewModel
import com.example.historyapp.ui.fragment.history.HistoryRepository
import com.example.historyapp.ui.fragment.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { AppDatabase.getDatabase(get()) } // Provide AppDatabase

    single { get<AppDatabase>().historyDao() }

    single { HistoryRepository(get()) }

    viewModel { HistoryViewModel(get()) }
    viewModel { HistoryDetailViewModel(get()) }
}