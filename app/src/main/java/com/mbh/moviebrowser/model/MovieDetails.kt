package com.mbh.moviebrowser.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("backdrop_path")
    val coverUrl: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("genres")
    val genres: List<Genre>
)
