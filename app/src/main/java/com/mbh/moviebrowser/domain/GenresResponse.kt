package com.mbh.moviebrowser.domain

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genre: List<Genre>,
)
