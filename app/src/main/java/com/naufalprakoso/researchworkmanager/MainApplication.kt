package com.naufalprakoso.researchworkmanager

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class MainApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerConfiguration: Configuration

    // Setup custom configuration for WorkManager with a DelegatingWorkerFactory
    override fun getWorkManagerConfiguration(): Configuration {
        return workerConfiguration
    }

}