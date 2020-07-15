package com.naufalprakoso.researchworkmanager.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.hero.usecase.HeroUseCase
import com.naufalprakoso.researchworkmanager.vo.Resource

class MainViewModel @ViewModelInject constructor(
    private val heroUseCase: HeroUseCase
) : ViewModel() {

    fun getHeroes(): LiveData<Resource<List<HeroEntity>>>? {
        return heroUseCase.getOfflineHeroes()
    }
}