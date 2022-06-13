package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.common.SharedViewModel
import com.mbh.moviebrowser.model.Genre
import com.mbh.moviebrowser.model.Movie
import com.mbh.moviebrowser.repository.GenreRepository
import com.mbh.moviebrowser.repository.MovieRepository

class MovieListViewModel(
    val movieRepository: MovieRepository,
    val genreRepository: GenreRepository,
    val sharedViewModel: SharedViewModel
) : ViewModel() {

    val genres: MutableLiveData<Map<Int, Genre>> = MutableLiveData<Map<Int, Genre>>()
    val movies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    fun loadPopularMovies() {
        sharedViewModel.showLoadingSpinner.value = true
        movieRepository.getPopularMovies(onSuccess = ::onPopularMoviesFetched, onError = ::onError)
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        sharedViewModel.showLoadingSpinner.value = false
        this.movies.postValue(movies)
    }

    fun loadGenres() {
        genreRepository.getGenres(onSuccess = ::onGenresFetched, onError = ::onError)
    }

    private fun onGenresFetched(genres: List<Genre>) {
        val genreMap = genres.associateBy({ it.id }, { it })
        this.genres.postValue(genreMap)
    }

    fun cleanUp() {
        movies.value = listOf()
        genres.value = mapOf()
        sharedViewModel.showLoadingSpinner.value = false
    }

    private fun onError() {
        val error = true
    }
}
