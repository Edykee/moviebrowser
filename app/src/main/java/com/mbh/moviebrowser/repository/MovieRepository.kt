package com.mbh.moviebrowser.repository

import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.domain.MovieResponse
import com.mbh.moviebrowser.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val movieService: MovieService) {

    fun getPopularMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getPopularMovies(page)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
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

                override fun onFailure(call: Call<MovieResponse>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }
}