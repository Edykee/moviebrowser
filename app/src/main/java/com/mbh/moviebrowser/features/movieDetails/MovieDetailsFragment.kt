package com.mbh.moviebrowser.features.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.network.GenreService
import com.mbh.moviebrowser.network.MovieService
import com.mbh.moviebrowser.network.RetrofitClient
import com.mbh.moviebrowser.repository.GenreRepository
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsFragment : Fragment(){
    val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieId = args.movieId;
        val movieService = RetrofitClient.getInstance().create(MovieService::class.java);
        val movieRepository = MovieRepository(movieService);

        movieDetailsViewModel =
            ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.setMovieRepository(movieRepository)
        movieDetailsViewModel.loadMovieDetail(movieId)
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBackButton(true)
    }
}
