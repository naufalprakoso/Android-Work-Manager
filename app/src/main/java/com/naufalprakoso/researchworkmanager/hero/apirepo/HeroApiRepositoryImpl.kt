package com.naufalprakoso.researchworkmanager.hero.apirepo

import com.naufalprakoso.researchworkmanager.api.ServiceBuilder
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

class HeroApiRepositoryImpl(
    private val serviceBuilder: ServiceBuilder
) : HeroApiRepository {

    override fun getAll(): List<HeroResponse> {
        val response = serviceBuilder.superheroService
            .getAll()
            .execute()

        return response.body() ?: listOf()
    }
}