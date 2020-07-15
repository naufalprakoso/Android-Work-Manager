package com.naufalprakoso.researchworkmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase
import com.naufalprakoso.researchworkmanager.utils.count

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val heroUseCase: HeroUseCase
) : CoroutineWorker(context, workerParameters) {

    private val tag = SeedDatabaseWorker::class.java.name

    override suspend fun doWork(): Result {
        return try {
            heroUseCase.getHeroes()
            Log.d(tag, "Success: $count")
            count++
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Log.d(tag, "runAttemptCount: $runAttemptCount")
                Log.d(tag, "Retry: $count")
                Result.retry()
            } else {
                Log.d(tag, "failure: $count")
                Result.failure()
            }
        }
    }

}