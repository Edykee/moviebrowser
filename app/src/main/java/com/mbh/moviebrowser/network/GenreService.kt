package com.mbh.moviebrowser.network

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.domain.GenresResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("https://api.themoviedb.org/3/genre/movie/list?")
    fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<GenresResponse>
}