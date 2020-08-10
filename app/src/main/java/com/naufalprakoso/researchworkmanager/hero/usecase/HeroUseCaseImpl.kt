package com.naufalprakoso.researchworkmanager.hero.usecase

import com.naufalprakoso.researchworkmanager.hero.apirepo.HeroApiRepository
import com.naufalprakoso.researchworkmanager.hero.repo.HeroRepository

class HeroUseCaseImpl(
    private val heroRepository: HeroRepository,
    private val heroApiRepository: HeroApiRepository
) : HeroUseCase {

    override fun fetchHeroes() {
        val response = heroApiRepository.getAll()
        heroRepository.insertOrUpdate(response)
    }
}