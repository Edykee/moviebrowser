package com.mbh.moviebrowser.features.personDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.PersonDetails
import com.mbh.moviebrowser.repository.PersonRepository

class PersonDetailsViewModel(var personRepository: PersonRepository) : ViewModel() {
    val personDetails: MutableLiveData<PersonDetails> = MutableLiveData<PersonDetails>()

    fun loadPersonDetails(personId: Int) {
        personRepository.getPersonDetails(personId, ::onPersonDetailsFetched, ::onError)
    }

    fun onPersonDetailsFetched(personDetails: PersonDetails) {
        this.personDetails.value = personDetails
    }

    fun onError() {
        val error = true;
    }
}