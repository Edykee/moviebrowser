package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.repository.GenreRepository
import com.mbh.moviebrowser.repository.MovieRepository

class MovieListViewModel() : ViewModel() {
    private lateinit var movieRepository: MovieRepository
    private lateinit var genreRepository: GenreRepository
    
    val genres: MutableLiveData<Map<Int, Genre>> = MutableLiveData<Map<Int, Genre>>()
    val movies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun setMovieRepository(movieRepository: MovieRepository) {
        this.movieRepository = movieRepository;
    }

    fun setGenreRepository(genreRepository: GenreRepository) {
        this.genreRepository = genreRepository;
    }

    fun loadPopularMovies() {
        isLoading.value = true
        movieRepository.getPopularMovies(onSuccess = ::onPopularMoviesFetched, onError = ::onError)
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        isLoading.value = false
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
        isLoading.value = false
    }

    private fun onError() {
        val error = true
    }
}
