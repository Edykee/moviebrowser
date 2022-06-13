package com.mbh.moviebrowser.model

import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    val id: Int,
    @SerializedName("cast")
    val casts: List<Cast>,
)
