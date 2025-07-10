package com.fatmawati.countryinfoapp.api

import com.fatmawati.countryinfoapp.data.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): Response<List<Country>>
}