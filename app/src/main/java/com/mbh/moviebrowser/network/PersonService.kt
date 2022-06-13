package com.mbh.moviebrowser.network

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.model.PersonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {

    @GET("3/person/{id}?")
    fun getPersonDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<PersonDetails>

}