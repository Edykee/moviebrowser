package com.mbh.moviebrowser.domain

import com.google.gson.annotations.SerializedName

data class Cast(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val coverUrl: String,
    @SerializedName("character")
    val character: String,
)
