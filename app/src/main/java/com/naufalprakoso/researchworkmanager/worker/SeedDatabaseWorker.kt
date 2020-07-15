package com.naufalprakoso.researchworkmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val heroUseCase: HeroUseCase
) : CoroutineWorker(context, workerParameters) {

    private val tag = SeedDatabaseWorker::class.java.name

    override suspend fun doWork(): Result {
        return try {
            heroUseCase.getHeroes()
            Log.d(tag, "Success: $runAttemptCount")
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Log.d(tag, "runAttemptCount: $runAttemptCount")
                Log.d(tag, "Retry: $runAttemptCount")
                Log.d(tag, "Retry: ${e.message}")
                Result.retry()
            } else {
                Log.d(tag, "Failure: $runAttemptCount")
                Log.d(tag, "Failure: ${e.message}")
                Result.failure()
            }
        }
    }

}