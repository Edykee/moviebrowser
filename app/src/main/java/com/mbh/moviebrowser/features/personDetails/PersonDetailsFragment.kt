package com.mbh.moviebrowser.features.personDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mbh.moviebrowser.common.SharedViewModel
import com.mbh.moviebrowser.databinding.FragmentPersonDetailsBinding
import com.mbh.moviebrowser.databinding.FragmentPersonDetailsBindingImpl
import com.mbh.moviebrowser.domain.PersonDetails
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsFragmentArgs
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.network.GenreService
import com.mbh.moviebrowser.network.MovieService
import com.mbh.moviebrowser.network.PersonService
import com.mbh.moviebrowser.network.RetrofitClient
import com.mbh.moviebrowser.repository.GenreRepository
import com.mbh.moviebrowser.repository.MovieRepository
import com.mbh.moviebrowser.repository.PersonRepository

class PersonDetailsFragment : Fragment() {
    private lateinit var fragmentPersonDetailsBinding: FragmentPersonDetailsBinding
    private val args: PersonDetailsFragmentArgs by navArgs()
    private lateinit var personDetailsViewModel: PersonDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initPersonDetailsViewModel()
        loadPersonDetails()
        fragmentPersonDetailsBinding =
            FragmentPersonDetailsBindingImpl.inflate(inflater, container, false)
        return fragmentPersonDetailsBinding.root
    }

    override fun onResume() {
        super.onResume()
        initObserver()
    }

    override fun onPause() {
        super.onPause()
        removeObserver()
    }

    private fun loadPersonDetails() {
        personDetailsViewModel.loadPersonDetails(args.personId)
    }

    private fun initObserver() {
        personDetailsViewModel.personDetails.observe(viewLifecycleOwner, Observer {
            personDetailsViewModel.personDetails.value
                ?.let { personDetails -> fragmentPersonDetailsBinding.person = personDetails }
        })
    }

    private fun removeObserver() {
        personDetailsViewModel.personDetails.removeObservers(viewLifecycleOwner)
    }

    private fun initPersonDetailsViewModel() {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val personService = RetrofitClient.getInstance().create(PersonService::class.java);
        val personRepository = PersonRepository(personService)
        personDetailsViewModel = PersonDetailsViewModel(personRepository, sharedViewModel)
    }
}