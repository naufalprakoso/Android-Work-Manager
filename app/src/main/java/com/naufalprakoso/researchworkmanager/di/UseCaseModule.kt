package com.naufalprakoso.researchworkmanager.di

import com.naufalprakoso.researchworkmanager.hero.apirepo.HeroApiRepository
import com.naufalprakoso.researchworkmanager.hero.repo.HeroRepository
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideHeroUseCase(
        heroRepository: HeroRepository,
        heroApiRepository: HeroApiRepository
    ): HeroUseCase {
        return HeroUseCaseImpl(
            heroRepository,
            heroApiRepository
        )
    }
}