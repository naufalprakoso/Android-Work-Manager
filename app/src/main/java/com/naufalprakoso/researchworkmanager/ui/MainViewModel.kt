package com.naufalprakoso.researchworkmanager.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.hero.repo.HeroRepository

class MainViewModel @ViewModelInject constructor(
    private val heroRepository: HeroRepository
) : ViewModel() {

    fun getHeroes(): LiveData<List<HeroEntity>> {
        return heroRepository.getHeroes()
    }
}