package com.example.historyapp.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.historyapp.data.db.daos.HeroDao
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.data.db.daos.HistoryDao
import com.example.historyapp.data.db.daos.LocationDao
import com.example.historyapp.data.model.HeroModel
import com.example.historyapp.data.model.LocationModel

@Database(entities = [HistoryModel::class, HeroModel::class, LocationModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
    abstract fun heroDao(): HeroDao
    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE history_table ADD COLUMN location TEXT")
            }
        }
    }
}