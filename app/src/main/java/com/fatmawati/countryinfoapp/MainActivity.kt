package com.fatmawati.countryinfoapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider // <--- IMPORT INI
import com.fatmawati.countryinfoapp.databinding.ActivityMainBinding
import com.fatmawati.countryinfoapp.viewmodel.CountryViewModel // <--- IMPORT INI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CountryViewModel // <--- DEKLARASIKAN INI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- BARIS PENTING UNTUK MENGGUNAKAN CountryViewModel ---
        // Inisialisasi ViewModel menggunakan ViewModelProvider
        // Ini memastikan ViewModel terikat pada siklus hidup Activity dan akan bertahan dari perubahan konfigurasi
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        // --- AKHIR BARIS PENTING ---

        // Observer untuk data negara
        viewModel.country.observe(this) { country ->
            if (country != null) {
                binding.countryName.text = country.name.common
                binding.countryOfficialName.text = "Nama Resmi: ${country.name.official}"
                binding.countryCapital.text = "Ibu Kota: ${country.capital?.joinToString() ?: "N/A"}"
                binding.countryPopulation.text = "Populasi: ${country.population?.toString() ?: "N/A"}"
                binding.countryRegion.text = "Benua: ${country.region ?: "N/A"}"
                binding.countrySubregion.text = "Sub-Benua: ${country.subregion ?: "N/A"}"

                val languages = country.languages?.values?.joinToString(", ") ?: "N/A"
                binding.countryLanguages.text = "Bahasa: $languages"

                val currencies = country.currencies?.map { "${it.value.name} (${it.key})" }?.joinToString(", ") ?: "N/A"
                binding.countryCurrencies.text = "Mata Uang: $currencies"

                binding.countryDetailsLayout.visibility = View.VISIBLE
                binding.errorMessage.visibility = View.GONE
            } else {
                binding.countryDetailsLayout.visibility = View.GONE
            }
        }

        // Observer untuk status loading
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.searchButton.isEnabled = !isLoading // Nonaktifkan tombol saat loading
        }

        // Observer untuk pesan error
        viewModel.errorMessage.observe(this) { message ->
            if (!message.isNullOrBlank()) {
                binding.errorMessage.text = message
                binding.errorMessage.visibility = View.VISIBLE
                binding.countryDetailsLayout.visibility = View.GONE
            } else {
                binding.errorMessage.visibility = View.GONE
            }
        }

        // Set listener untuk tombol pencarian
        binding.searchButton.setOnClickListener {
            val countryName = binding.countryEditText.text.toString().trim()
            viewModel.searchCountry(countryName) // <--- PANGGIL FUNGSI ViewModel DI SINI
        }
    }
}