package com.naufalprakoso.researchworkmanager.di

import com.naufalprakoso.researchworkmanager.utils.ContextProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class CoroutineModule {

    @Singleton
    @Provides
    fun provideContextProviders() = ContextProviders()
}