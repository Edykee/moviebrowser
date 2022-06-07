package com.mbh.moviebrowser.features.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.databinding.FragmentMovieDetailsBinding
import com.mbh.moviebrowser.network.MovieService
import com.mbh.moviebrowser.network.RetrofitClient
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsFragment : Fragment() {
    val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var fragmentMovieDetailsBinding: FragmentMovieDetailsBinding;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMovieDetailsViewModel()
        loadMovieDetails()

        fragmentMovieDetailsBinding =
            FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return fragmentMovieDetailsBinding.root
    }

    private fun initMovieDetailsViewModel() {
        val movieService = RetrofitClient.getInstance().create(MovieService::class.java);
        val movieRepository = MovieRepository(movieService);

        movieDetailsViewModel =
            ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.setMovieRepository(movieRepository)
    }

    private fun loadMovieDetails() {
        movieDetailsViewModel.loadMovieDetail(args.movieId)
    }

    private fun initObservers() {
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            movieDetailsViewModel.movieDetails.getValue()
                ?.let { movieDetails -> fragmentMovieDetailsBinding.movieDetails = movieDetails }
        })
    }

    private fun removeObservers() {
        movieDetailsViewModel.movieDetails.removeObservers(viewLifecycleOwner)
    }

    override fun onResume() {
        super.onResume()
        initObservers()
        (activity as MainActivity).showBackButton(true)
    }

    override fun onPause() {
        super.onPause()
        movieDetailsViewModel.cleanUp()
        removeObservers();
    }
}
