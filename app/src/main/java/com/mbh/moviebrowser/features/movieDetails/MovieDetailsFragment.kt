package com.mbh.moviebrowser.features.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.common.SharedViewModel
import com.mbh.moviebrowser.databinding.FragmentMovieDetailsBinding
import com.mbh.moviebrowser.features.movieList.MovieListFragmentDirections
import com.mbh.moviebrowser.network.MovieService
import com.mbh.moviebrowser.network.RetrofitClient
import com.mbh.moviebrowser.repository.MovieRepository

class MovieDetailsFragment : Fragment(), PersonClickHandler {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var fragmentMovieDetailsBinding: FragmentMovieDetailsBinding;
    private lateinit var creditsAdapter: CreditsAdapter

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
        creditsAdapter = CreditsAdapter(listOf(), this)
        credits.adapter = creditsAdapter
    }

    private fun initMovieDetailsViewModel() {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val movieService = RetrofitClient.getInstance().create(MovieService::class.java);
        val movieRepository = MovieRepository(movieService);
        movieDetailsViewModel =
            ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.setMovieRepository(movieRepository)
        movieDetailsViewModel.setSharedViewModel(sharedViewModel)

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
    }

    private fun removeObservers() {
        movieDetailsViewModel.movieDetails.removeObservers(viewLifecycleOwner)
        movieDetailsViewModel.movieCredits.removeObservers(viewLifecycleOwner)
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

    override fun onClick(personId: Int) {
        val action = MovieDetailsFragmentDirections.toPersonDetails(personId)
        activity?.findNavController(R.id.navHostFragment)?.navigate(action)
    }
}
