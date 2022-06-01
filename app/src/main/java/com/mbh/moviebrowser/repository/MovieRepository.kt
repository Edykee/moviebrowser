package com.mbh.moviebrowser.repository

import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.domain.MovieList
import com.mbh.moviebrowser.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val movieService: MovieService) {

    fun getPopularMovies(
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getPopularMovies()
            .enqueue(object : Callback<MovieList> {
                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.results)
                            return
                        }
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieList>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }
}