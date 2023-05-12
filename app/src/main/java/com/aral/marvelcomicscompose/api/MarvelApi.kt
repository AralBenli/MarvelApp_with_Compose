package com.aral.marvelcomicscompose.api

import com.aral.marvelcomicscompose.modal.CharactersApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AralBenli on 12.05.2023.
 */
interface MarvelApi {
    @GET("characters")
    fun getCharacters(
        @Query("nameStartsWith"
        ) name: String):
            Call<CharactersApiResponse>




}