package com.mbh.moviebrowser.network

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.model.MovieCreditsResponse
import com.mbh.moviebrowser.model.MovieDetails
import com.mbh.moviebrowser.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular?")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieListResponse>

    @GET("3/movie/{id}?")
    fun getMovieDetail(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieDetails>

    @GET("3/movie/{id}/credits?")
    fun getMovieCredits(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieCreditsResponse>
}