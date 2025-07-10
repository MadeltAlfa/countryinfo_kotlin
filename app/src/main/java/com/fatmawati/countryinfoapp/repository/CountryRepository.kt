package com.fatmawati.countryinfoapp.repository

import com.fatmawati.countryinfoapp.api.RetrofitClient
import com.fatmawati.countryinfoapp.data.Country
import retrofit2.Response

class CountryRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getCountryByName(name: String): Response<List<Country>> {
        return apiService.getCountryByName(name)
    }
}