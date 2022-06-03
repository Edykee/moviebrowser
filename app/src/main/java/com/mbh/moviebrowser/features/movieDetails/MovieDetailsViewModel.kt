package com.mbh.moviebrowser.features.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.MovieDetail
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsViewModel : ViewModel() {
    val movieDetail: MutableLiveData<MovieDetail> = MutableLiveData<MovieDetail>()
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

    private fun onPopularMoviesFetched(movieDetail: MovieDetail) {
        this.movieDetail.postValue(movieDetail)

    }

    private fun onError() {
        val error = true
    }
}
