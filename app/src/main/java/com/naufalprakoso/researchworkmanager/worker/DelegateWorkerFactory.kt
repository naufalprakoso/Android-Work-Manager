package com.naufalprakoso.researchworkmanager.worker

import androidx.work.DelegatingWorkerFactory
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Wrapper for Worker Factory
 *
 * @param heroUseCase use case for Hero
 */
@Singleton
class DelegateWorkerFactory @Inject constructor(
    heroUseCase: HeroUseCase
) : DelegatingWorkerFactory() {
    init {
        addFactory(CustomWorkerFactory(heroUseCase))
    }
}