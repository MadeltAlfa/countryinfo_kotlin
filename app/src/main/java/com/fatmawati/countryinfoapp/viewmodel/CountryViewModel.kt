package com.fatmawati.countryinfoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatmawati.countryinfoapp.data.Country
import com.fatmawati.countryinfoapp.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel : ViewModel() {

    private val repository = CountryRepository()

    private val _country = MutableLiveData<Country?>()
    val country: LiveData<Country?> = _country

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun searchCountry(name: String) {
        if (name.isBlank()) {
            _errorMessage.value = "Nama negara tidak boleh kosong." // Ini masih hardcoded di ViewModel
            _country.value = null
            return
        }

        _isLoading.value = true
        _errorMessage.value = null
        _country.value = null

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getCountryByName(name)
                }

                if (response.isSuccessful) {
                    val countries = response.body()
                    if (!countries.isNullOrEmpty()) {
                        _country.value = countries[0]
                    } else {
                        _errorMessage.value = "Negara '$name' tidak ditemukan." // Hardcoded
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    _errorMessage.value = "Gagal mengambil data: ${response.code()} - ${response.message()}\n${errorBody}" // Hardcoded
                }
            } catch (e: Exception) {
                _errorMessage.value = "Terjadi kesalahan jaringan: ${e.message}" // Hardcoded
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}