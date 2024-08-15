package com.example.historyapp.di

import com.example.historyapp.data.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        AppDatabase.getDatabase(get())
    }

    single {
        get<AppDatabase>().historyDao()
    }
    single {
        get<AppDatabase>().heroDao()
    }

}