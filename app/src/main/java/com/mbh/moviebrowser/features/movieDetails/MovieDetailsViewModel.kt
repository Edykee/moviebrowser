package com.mbh.moviebrowser.features.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.MovieDetails
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsViewModel : ViewModel() {

    val movieDetails: MutableLiveData<MovieDetails> = MutableLiveData<MovieDetails>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private lateinit var movieRepository: MovieRepository

    fun setMovieRepository(movieRepository: MovieRepository) {
        this.movieRepository = movieRepository;
    }

    fun loadMovieDetail(movieId: Long) {
        isLoading.value = true;
        movieRepository.getMovieDetail(
            movieId,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    private fun onPopularMoviesFetched(movieDetails: MovieDetails) {
        isLoading.value = false;
        this.movieDetails.postValue(movieDetails)
    }

    fun cleanUp() {
        movieDetails.value = MovieDetails(0, "", "", "", 0f, listOf())
        isLoading.value = false;
    }

    private fun onError() {
        val error = true
    }
}
