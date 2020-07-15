package com.naufalprakoso.researchworkmanager.hero.repo

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

interface HeroRepository {

    fun getHeroes(): LiveData<List<HeroEntity>>
    fun insertOrUpdate(data: List<HeroResponse>)
}