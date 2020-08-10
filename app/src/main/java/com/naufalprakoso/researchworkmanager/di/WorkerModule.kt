package com.naufalprakoso.researchworkmanager.di

import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import com.naufalprakoso.researchworkmanager.workmanager.factory.ChildWorkerFactory
import com.naufalprakoso.researchworkmanager.workmanager.factory.CustomWorkerFactory
import com.naufalprakoso.researchworkmanager.workmanager.factory.WrapperWorkerFactory
import com.naufalprakoso.researchworkmanager.workmanager.worker.SeedDatabaseWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@InstallIn(ApplicationComponent::class)
@Module
abstract class WorkerModule {

    @Binds
    abstract fun bindWorkerFactoryModule(workerFactory: WrapperWorkerFactory): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(SeedDatabaseWorker::class)
    abstract fun bindFetchWorker(factory: CustomWorkerFactory): ChildWorkerFactory
}