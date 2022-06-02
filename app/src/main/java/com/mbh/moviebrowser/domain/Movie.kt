package com.mbh.moviebrowser.domain

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val coverUrl: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("genre_ids")
    val genreIds: List<Int>
)
