package com.mbh.moviebrowser.network

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.domain.MovieList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.themoviedb.org/3/movie/76341?api_key
//https://api.themoviedb.org/3/movie/popular?page=1&api_key
//https://api.themoviedb.org/3/genre/movie/list?api_key
interface MovieService {
    @GET("3/movie/popular?")
    fun getPopularMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Call<MovieList>
}