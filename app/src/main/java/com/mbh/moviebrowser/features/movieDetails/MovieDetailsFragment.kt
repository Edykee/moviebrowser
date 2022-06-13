package com.mbh.moviebrowser.features.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.databinding.FragmentMovieDetailsBinding
import com.mbh.moviebrowser.network.MovieService
import com.mbh.moviebrowser.network.RetrofitClient
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var fragmentMovieDetailsBinding: FragmentMovieDetailsBinding;
    private lateinit var creditsAdapter : CreditsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initMovieDetailsViewModel()
        loadMovieDetails()
        fragmentMovieDetailsBinding =
            FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return fragmentMovieDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createView(view: View) {
        val credits: RecyclerView = view.findViewById(R.id.credits)
        creditsAdapter = CreditsAdapter(listOf())
        credits.adapter = creditsAdapter
    }

    private fun initMovieDetailsViewModel() {
        val movieService = RetrofitClient.getInstance().create(MovieService::class.java);
        val movieRepository = MovieRepository(movieService);

        movieDetailsViewModel =
            ViewModelProvider(requireActivity())[MovieDetailsViewModel::class.java]
        movieDetailsViewModel.setMovieRepository(movieRepository)
    }

    private fun loadMovieDetails() {
        movieDetailsViewModel.loadMovieDetail(args.movieId)
        movieDetailsViewModel.loadMovieCredits(args.movieId)
    }

    private fun initObservers() {
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            movieDetailsViewModel.movieDetails.value
                ?.let { movieDetails ->
                    fragmentMovieDetailsBinding.movieDetails = movieDetails
                }
        })

        movieDetailsViewModel.movieCredits.observe(viewLifecycleOwner, Observer {
            movieDetailsViewModel.movieCredits.value
                ?.let { movieCredits ->
                    creditsAdapter.updateCasts(movieCredits.casts)
                }
        })

        movieDetailsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            movieDetailsViewModel.isLoading.value
                ?.let { isLoading ->
                    if (isLoading) {
                        (activity as MainActivity).showLoadingSpinner()
                    } else {
                        (activity as MainActivity).hideLoadingSpinner()
                    }
                }
        })
    }

    private fun removeObservers() {
        movieDetailsViewModel.movieDetails.removeObservers(viewLifecycleOwner)
    }

    override fun onResume() {
        super.onResume()
        initObservers()
        (activity as MainActivity).showBackButton()
    }

    override fun onPause() {
        super.onPause()
        movieDetailsViewModel.cleanUp()
        removeObservers();
    }
}
