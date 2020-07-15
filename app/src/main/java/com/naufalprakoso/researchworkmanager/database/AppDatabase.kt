package com.naufalprakoso.researchworkmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.*
import com.naufalprakoso.researchworkmanager.database.dao.HeroDao
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.utils.UNIQUE_WORK_NAME
import com.naufalprakoso.researchworkmanager.utils.workRequestId
import com.naufalprakoso.researchworkmanager.worker.SeedDatabaseWorker
import java.util.concurrent.TimeUnit

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
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        // Must be connected to the internet
                        val workerConstraints = Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()

                        // If work request retry the Worker, it will give 1 minutes backoff delay
                        val workerRequest = PeriodicWorkRequestBuilder<SeedDatabaseWorker>(
                            15,
                            TimeUnit.MINUTES
                        ).setBackoffCriteria(
                            BackoffPolicy.LINEAR,
                            1,
                            TimeUnit.MINUTES
                        ).setConstraints(workerConstraints).build()
                        workRequestId = workerRequest.id

                        // If there is uncompleted work with the same unique name, replace it
                        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                            UNIQUE_WORK_NAME,
                            ExistingPeriodicWorkPolicy.REPLACE,
                            workerRequest
                        )
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}