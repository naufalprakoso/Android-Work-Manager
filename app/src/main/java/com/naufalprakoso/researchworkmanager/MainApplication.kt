package com.naufalprakoso.researchworkmanager

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.naufalprakoso.researchworkmanager.workmanager.factory.WrapperWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class MainApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WrapperWorkerFactory

    // Setup custom configuration for WorkManager with a DelegatingWorkerFactory
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }
}