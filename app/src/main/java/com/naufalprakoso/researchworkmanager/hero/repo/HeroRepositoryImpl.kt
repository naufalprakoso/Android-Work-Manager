package com.naufalprakoso.researchworkmanager.hero.repo

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.database.dao.HeroDao
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.hero.mapper.HeroMapper
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

class HeroRepositoryImpl(private val heroDao: HeroDao) : HeroRepository {

    private val heroMapper by lazy {
        HeroMapper()
    }

    override fun getHeroes(): LiveData<List<HeroEntity>> {
        return heroDao.getHeroes()
    }

    override fun insertOrUpdate(data: List<HeroResponse>) {
        if (data.isNotEmpty()) {
            val hero = heroMapper.convertToHeroEntity(data)
            heroDao.insertHeroes(hero)
        }
    }
}