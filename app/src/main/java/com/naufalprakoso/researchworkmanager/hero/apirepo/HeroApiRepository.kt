package com.naufalprakoso.researchworkmanager.hero.apirepo

import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

interface HeroApiRepository {

    fun getAll(): List<HeroResponse>
}