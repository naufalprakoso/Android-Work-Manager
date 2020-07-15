package com.naufalprakoso.researchworkmanager.di

import androidx.work.Configuration
import com.naufalprakoso.researchworkmanager.worker.DelegateWorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class WorkerModule {

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        delegateWorkerFactory: DelegateWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(delegateWorkerFactory)
            .build()
    }

}