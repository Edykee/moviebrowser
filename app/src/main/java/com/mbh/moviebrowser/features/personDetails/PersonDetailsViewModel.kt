package com.mbh.moviebrowser.features.personDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.common.SharedViewModel
import com.mbh.moviebrowser.domain.PersonDetails
import com.mbh.moviebrowser.repository.PersonRepository

class PersonDetailsViewModel(
    val personRepository: PersonRepository,
    val sharedViewModel: SharedViewModel
) : ViewModel() {
    val personDetails: MutableLiveData<PersonDetails> = MutableLiveData<PersonDetails>()

    fun loadPersonDetails(personId: Int) {
        sharedViewModel.showLoadingSpinner.value = true
        personRepository.getPersonDetails(personId, ::onPersonDetailsFetched, ::onError)
    }

    fun onPersonDetailsFetched(personDetails: PersonDetails) {
        sharedViewModel.showLoadingSpinner.value = false
        this.personDetails.value = personDetails
    }

    fun onError() {
        sharedViewModel.showLoadingSpinner.value = false
        val error = true;
    }
}