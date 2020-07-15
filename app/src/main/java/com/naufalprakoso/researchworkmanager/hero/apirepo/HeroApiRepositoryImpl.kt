package com.naufalprakoso.researchworkmanager.hero.apirepo

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.api.ServiceBuilder
import com.naufalprakoso.researchworkmanager.network.ApiResponse
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

class HeroApiRepositoryImpl(
    private val serviceBuilder: ServiceBuilder
) : HeroApiRepository {

    override fun getAll(): LiveData<ApiResponse<List<HeroResponse>>>? {
        return serviceBuilder.superheroService.getAll()
    }
}