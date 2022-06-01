package com.mbh.moviebrowser.domain

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    val totalPages: Int,
)