package com.mbh.moviebrowser.network

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.domain.MovieDetail
import com.mbh.moviebrowser.domain.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular?")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MoviesResponse>

    @GET("3/movie/{id}?")
    fun getMovieDetail(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieDetail>
}