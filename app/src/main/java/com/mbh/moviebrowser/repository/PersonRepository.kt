package com.mbh.moviebrowser.repository

import com.mbh.moviebrowser.model.PersonDetails
import com.mbh.moviebrowser.network.PersonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(private val personService: PersonService) {
    fun getPersonDetails(id: Int,
        onSuccess: (personDetails: PersonDetails) -> Unit,
        onError: () -> Unit
    ) {
        personService.getPersonDetails(id)
            .enqueue(object : Callback<PersonDetails> {
                override fun onResponse(
                    call: Call<PersonDetails>,
                    response: Response<PersonDetails>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                            return
                        }
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<PersonDetails>, throwable: Throwable) {
                    onError.invoke()
                }
            })
    }
}