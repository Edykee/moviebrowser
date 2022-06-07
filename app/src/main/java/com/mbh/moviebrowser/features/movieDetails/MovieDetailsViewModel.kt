package com.mbh.moviebrowser.features.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.MovieDetails
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsViewModel : ViewModel() {
    val movieDetails: MutableLiveData<MovieDetails> = MutableLiveData<MovieDetails>()
    private lateinit var movieRepository: MovieRepository

    fun setMovieRepository(movieRepository: MovieRepository) {
        this.movieRepository = movieRepository;
    }

    fun loadMovieDetail(movieId: Long) {
        movieRepository.getMovieDetail(
            movieId,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    private fun onPopularMoviesFetched(movieDetails: MovieDetails) {
        this.movieDetails.postValue(movieDetails)

    }

    public fun cleanUp() {
        movieDetails.value = MovieDetails(0, "", "", "", 0f, listOf())
    }

    private fun onError() {
        val error = true
    }
}
