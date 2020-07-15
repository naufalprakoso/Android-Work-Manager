package com.naufalprakoso.researchworkmanager.hero.usecase

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.vo.Resource

interface HeroUseCase {
    fun getOfflineHeroes(): LiveData<Resource<List<HeroEntity>>>
    fun getHeroes(): LiveData<Resource<List<HeroEntity>>>
}