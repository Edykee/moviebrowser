package com.mbh.moviebrowser.features.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.network.GenreService
import com.mbh.moviebrowser.network.MovieService
import com.mbh.moviebrowser.network.RetrofitClient
import com.mbh.moviebrowser.repository.GenreRepository
import com.mbh.moviebrowser.repository.MovieRepository

class MovieListFragment : Fragment() {
    private lateinit var popularMoviesAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieService = RetrofitClient.getInstance().create(MovieService::class.java);
        val genresService = RetrofitClient.getInstance().create(GenreService::class.java);
        val movieRepository = MovieRepository(movieService);
        val genreRepository = GenreRepository(genresService);

        movieListViewModel =
            ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)

        movieListViewModel.setGenreRepository(genreRepository)
        movieListViewModel.setMovieRepository(movieRepository)
        movieListViewModel.loadGenres()
        movieListViewModel.loadPopularMovies()

        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createView(view)
        initObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createView(view: View) {
        val popularMovies: RecyclerView = view.findViewById(R.id.popular_movies)
        popularMoviesAdapter = MovieListAdapter(listOf(), mapOf())
        popularMovies.adapter = popularMoviesAdapter
    }

    private fun initObservers() {
        movieListViewModel.genres.observe(viewLifecycleOwner, Observer {
            movieListViewModel.genres.getValue()
                ?.let { genreMap -> popularMoviesAdapter.updateGenres(genreMap) }
        })

        movieListViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieListViewModel.movies.getValue()
                ?.let { movies -> popularMoviesAdapter.updateMovies(movies) }
        })
    }
}
