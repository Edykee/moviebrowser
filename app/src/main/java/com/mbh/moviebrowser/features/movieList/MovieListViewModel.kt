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

    fun setMovieRepository(movieRepository: MovieRepository) {
        this.movieRepository = movieRepository;
    }

    fun setGenreRepository(genreRepository: GenreRepository) {
        this.genreRepository = genreRepository;
    }

    fun loadPopularMovies() {
        movieRepository.getPopularMovies(onSuccess = ::onPopularMoviesFetched, onError = ::onError)
    }

    fun loadGenres() {
        genreRepository.getGenres(onSuccess = ::onPopularGenresFetched, onError = ::onError)
    }


    private fun onPopularGenresFetched(genres: List<Genre>) {
        val genreMap = genres.associateBy({ it.id }, { it })
        this.genres.postValue(genreMap)

    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        this.movies.postValue(movies)
    }

    private fun onError() {
        val error = true
    }
}
