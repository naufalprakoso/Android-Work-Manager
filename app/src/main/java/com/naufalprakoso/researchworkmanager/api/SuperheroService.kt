package com.naufalprakoso.researchworkmanager.api

import com.naufalprakoso.researchworkmanager.network.response.HeroResponse
import retrofit2.Call
import retrofit2.http.GET

interface SuperheroService {
    @GET("all.json")
    fun getAll(): Call<List<HeroResponse>>
}