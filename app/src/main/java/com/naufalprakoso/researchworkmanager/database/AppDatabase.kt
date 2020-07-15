package com.naufalprakoso.researchworkmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naufalprakoso.researchworkmanager.database.dao.HeroDao
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity

@Database(
    entities = [
        HeroEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHeroDao(): HeroDao

    companion object {
        private const val databaseName = "Superhero.db"
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                databaseName
            ).fallbackToDestructiveMigration().build()
        }
    }
}