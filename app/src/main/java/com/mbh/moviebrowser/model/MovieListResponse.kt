package com.mbh.moviebrowser.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    val totalPages: Int,
)