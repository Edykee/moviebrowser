package com.mbh.moviebrowser.repository

import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.GenresResponse
import com.mbh.moviebrowser.network.GenreService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreRepository(private val genreService: GenreService)  {
    fun getGenres(
        onSuccess: (movies: List<Genre>) -> Unit,
        onError: () -> Unit
    ) {
        genreService.getGenres()
            .enqueue(object : Callback<GenresResponse> {
                override fun onResponse(
                    call: Call<GenresResponse>,
                    response: Response<GenresResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.genres)
                            return
                        }
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GenresResponse>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }
}