package com.mbh.moviebrowser.repository

import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.domain.MovieCreditsResponse
import com.mbh.moviebrowser.domain.MovieDetails
import com.mbh.moviebrowser.domain.MovieListResponse
import com.mbh.moviebrowser.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val movieService: MovieService) {

    fun getPopularMovies(
        page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getPopularMovies(page)
            .enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                            return
                        }
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieDetail(
        id: Long, onSuccess: (movieDetails: MovieDetails) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getMovieDetail(id)
            .enqueue(object : Callback<MovieDetails> {
                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                            return
                        }
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieCredits(
        id: Long, onSuccess: (movieCreditsResposne: MovieCreditsResponse) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getMovieCredits(id)
            .enqueue(object : Callback<MovieCreditsResponse> {
                override fun onResponse(
                    call: Call<MovieCreditsResponse>,
                    response: Response<MovieCreditsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                            return
                        }
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieCreditsResponse>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }
}