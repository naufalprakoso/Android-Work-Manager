package com.naufalprakoso.researchworkmanager.network.response

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String
)