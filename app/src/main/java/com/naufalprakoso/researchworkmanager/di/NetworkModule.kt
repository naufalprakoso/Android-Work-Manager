package com.naufalprakoso.researchworkmanager.di

import com.naufalprakoso.researchworkmanager.api.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideServiceBuilder() = ServiceBuilder()
}