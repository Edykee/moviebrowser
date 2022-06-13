package com.mbh.moviebrowser.domain

import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    val id: Int,
    @SerializedName("cast")
    val casts: List<Cast>,
)
