package com.naufalprakoso.researchworkmanager.workmanager.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase
import com.naufalprakoso.researchworkmanager.workmanager.worker.SeedDatabaseWorker
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(
    private val heroUseCase: HeroUseCase
) : ChildWorkerFactory {

    override fun create(
        appContext: Context,
        params: WorkerParameters
    ): ListenableWorker {
        return SeedDatabaseWorker(
            appContext,
            params,
            heroUseCase
        )
    }
}