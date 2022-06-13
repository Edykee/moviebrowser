package com.mbh.moviebrowser.domain

import com.google.gson.annotations.SerializedName

data class PersonDetails(
    val id: Int,
    val birthday: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    val name: String,
    @SerializedName("known_for_department")
    val department: String,
    @SerializedName("profile_path")
    val profileUrl: String,
    val biography: String
)
