package com.mbh.moviebrowser.domain

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val coverUrl: String,
    val rating: Float
)
