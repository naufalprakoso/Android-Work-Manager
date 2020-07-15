package com.naufalprakoso.researchworkmanager.di

import com.naufalprakoso.researchworkmanager.api.ServiceBuilder
import com.naufalprakoso.researchworkmanager.hero.apirepo.HeroApiRepository
import com.naufalprakoso.researchworkmanager.hero.apirepo.HeroApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class ApiRepositoryModule {

    @Provides
    fun provideHeroApiRepository(serviceBuilder: ServiceBuilder): HeroApiRepository {
        return HeroApiRepositoryImpl(
            serviceBuilder
        )
    }
}