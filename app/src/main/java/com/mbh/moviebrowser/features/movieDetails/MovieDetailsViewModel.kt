package com.mbh.moviebrowser.features.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.common.SharedViewModel
import com.mbh.moviebrowser.domain.MovieCreditsResponse
import com.mbh.moviebrowser.domain.MovieDetails
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsViewModel(
    val movieRepository: MovieRepository,
    val sharedViewModel: SharedViewModel
) : ViewModel() {

    val movieDetails: MutableLiveData<MovieDetails> = MutableLiveData<MovieDetails>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val movieCredits: MutableLiveData<MovieCreditsResponse> =
        MutableLiveData<MovieCreditsResponse>()

    fun loadMovieDetail(movieId: Long) {
        sharedViewModel.showLoadingSpinner.value = true;
        movieRepository.getMovieDetail(
            movieId,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    fun loadMovieCredits(movieId: Long) {
        movieRepository.getMovieCredits(
            movieId,
            onSuccess = ::onMovieCreditsFetched,
            onError = ::onError
        )
    }

    private fun onMovieCreditsFetched(movieCreditsResponse: MovieCreditsResponse) {
        this.movieCredits.postValue(movieCreditsResponse)
    }

    private fun onPopularMoviesFetched(movieDetails: MovieDetails) {
        sharedViewModel.showLoadingSpinner.value = false;
        this.movieDetails.postValue(movieDetails)
    }

    fun cleanUp() {
        movieDetails.value = MovieDetails(0, "", "", "", 0f, listOf())
        movieCredits.value = MovieCreditsResponse(0, listOf())
        sharedViewModel.showLoadingSpinner.value = false;
    }

    private fun onError() {
        val error = true
    }
}
