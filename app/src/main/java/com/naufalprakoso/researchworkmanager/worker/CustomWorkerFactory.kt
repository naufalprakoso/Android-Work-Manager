package com.naufalprakoso.researchworkmanager.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase
import javax.inject.Inject

/**
 * Create a custom worker factory in order to create
 * additional parameters in your worker.
 *
 * @param heroUseCase use case for Hero
 */
class CustomWorkerFactory(private val heroUseCase: HeroUseCase) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            SeedDatabaseWorker::class.java.name -> {
                SeedDatabaseWorker(appContext, workerParameters, heroUseCase)
            }

            // Return null, so that the base class can delegate to the default WorkerFactory.
            else -> null
        }
    }

}