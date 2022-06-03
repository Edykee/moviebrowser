package com.mbh.moviebrowser.repository

import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.domain.MovieDetail
import com.mbh.moviebrowser.domain.MoviesResponse
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
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
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

                override fun onFailure(call: Call<MoviesResponse>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieDetail(
        id: Long, onSuccess: (movieDetail: MovieDetail) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getMovieDetail(id)
            .enqueue(object : Callback<MovieDetail> {
                override fun onResponse(
                    call: Call<MovieDetail>,
                    response: Response<MovieDetail>
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

                override fun onFailure(call: Call<MovieDetail>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }
}