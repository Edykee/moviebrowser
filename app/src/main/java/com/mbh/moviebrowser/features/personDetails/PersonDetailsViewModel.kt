package com.mbh.moviebrowser.features.personDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.common.SharedViewModel
import com.mbh.moviebrowser.model.PersonDetails
import com.mbh.moviebrowser.repository.PersonRepository

class PersonDetailsViewModel() : ViewModel() {
    private lateinit var personRepository: PersonRepository
    private lateinit var sharedViewModel: SharedViewModel

    val personDetails: MutableLiveData<PersonDetails> = MutableLiveData<PersonDetails>()

    fun setPersonRepository(personRepository: PersonRepository) {
        this.personRepository = personRepository
    }

    fun setSharedViewModel(sharedViewModel: SharedViewModel) {
        this.sharedViewModel = sharedViewModel;
    }

    fun loadPersonDetails(personId: Int) {
        sharedViewModel.showLoadingSpinner.value = true
        personRepository.getPersonDetails(personId, ::onPersonDetailsFetched, ::onError)
    }

    private fun onPersonDetailsFetched(personDetails: PersonDetails) {
        sharedViewModel.showLoadingSpinner.value = false
        this.personDetails.value = personDetails
    }

    private fun onError() {
        sharedViewModel.showLoadingSpinner.value = false
        val error = true;
    }
}