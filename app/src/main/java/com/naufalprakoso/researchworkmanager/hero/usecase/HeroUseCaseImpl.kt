package com.naufalprakoso.researchworkmanager.hero.usecase

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.hero.apirepo.HeroApiRepository
import com.naufalprakoso.researchworkmanager.hero.repo.HeroRepository
import com.naufalprakoso.researchworkmanager.network.ApiResponse
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse
import com.naufalprakoso.researchworkmanager.utils.ContextProviders
import com.naufalprakoso.researchworkmanager.utils.NetworkBoundResource
import com.naufalprakoso.researchworkmanager.vo.Resource

class HeroUseCaseImpl(
    private val heroRepository: HeroRepository,
    private val heroApiRepository: HeroApiRepository,
    private val contextProviders: ContextProviders
) : HeroUseCase {

    override fun getOfflineHeroes(): LiveData<Resource<List<HeroEntity>>> {
        return object :
            NetworkBoundResource<List<HeroEntity>, List<HeroResponse>>(contextProviders) {
            override fun loadFromDB(): LiveData<List<HeroEntity>> {
                return heroRepository.getHeroes()
            }

            override fun shouldFetch(data: List<HeroEntity>?): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<HeroResponse>>>? = null

            override fun saveCallResult(data: List<HeroResponse>) {}
        }.asLiveData()
    }

    override fun getHeroes(): LiveData<Resource<List<HeroEntity>>> {
        return object :
            NetworkBoundResource<List<HeroEntity>, List<HeroResponse>>(contextProviders) {
            override fun loadFromDB(): LiveData<List<HeroEntity>> {
                return heroRepository.getHeroes()
            }

            override fun shouldFetch(data: List<HeroEntity>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<HeroResponse>>>? =
                heroApiRepository.getAll()

            override fun saveCallResult(data: List<HeroResponse>) {
                heroRepository.insertOrUpdate(data)
            }
        }.asLiveData()
    }
}