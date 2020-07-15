package com.naufalprakoso.researchworkmanager.api

import androidx.lifecycle.LiveData
import com.naufalprakoso.researchworkmanager.network.ApiResponse
import com.naufalprakoso.researchworkmanager.network.response.HeroResponse
import retrofit2.http.GET

interface SuperheroService {
    @GET("all.json")
    fun getAll(): LiveData<ApiResponse<List<HeroResponse>>>
}