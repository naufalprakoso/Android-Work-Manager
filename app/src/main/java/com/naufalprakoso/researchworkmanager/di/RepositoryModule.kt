package com.naufalprakoso.researchworkmanager.di

import com.naufalprakoso.researchworkmanager.database.AppDatabase
import com.naufalprakoso.researchworkmanager.hero.repo.HeroRepository
import com.naufalprakoso.researchworkmanager.hero.repo.HeroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideHeroRepository(
        appDatabase: AppDatabase,
        ioScope: CoroutineScope
    ): HeroRepository {
        return HeroRepositoryImpl(
            appDatabase.getHeroDao(),
            ioScope
        )
    }
}