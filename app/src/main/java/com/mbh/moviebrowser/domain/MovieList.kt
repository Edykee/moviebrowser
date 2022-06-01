package com.mbh.moviebrowser.domain

data class MovieList (
    val page: Int,
    val results: List<Movie>
)