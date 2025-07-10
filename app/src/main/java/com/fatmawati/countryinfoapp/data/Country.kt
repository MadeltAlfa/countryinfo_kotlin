package com.fatmawati.countryinfoapp.data

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: Name,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("population") val population: Long?,
    @SerializedName("currencies") val currencies: Map<String, CurrencyDetail>?,
    @SerializedName("region") val region: String?,
    @SerializedName("subregion") val subregion: String?,
    @SerializedName("languages") val languages: Map<String, String>?
)

data class Name(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String
)

data class CurrencyDetail(
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String?
)