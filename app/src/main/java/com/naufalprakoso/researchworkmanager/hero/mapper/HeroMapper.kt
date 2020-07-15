package com.naufalprakoso.researchworkmanager.hero.mapper

import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

class HeroMapper {

    fun convertToHeroEntity(heroesResponse: List<HeroResponse>): List<HeroEntity> {
        return heroesResponse.map {
            HeroEntity(it.id, it.name, it.slug)
        }
    }
}