package com.mbh.moviebrowser.domain

data class MovieCreditsResponse(
    val id: Int,
    val casts: List<Cast>,
)
