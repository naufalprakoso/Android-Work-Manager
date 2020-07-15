package com.naufalprakoso.researchworkmanager.hero.apirepo

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.network.ApiResponse
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse

interface HeroApiRepository {

    fun getAll(): LiveData<ApiResponse<List<HeroResponse>>>?
}