package com.example.graphql_example.data

import com.example.CountriesQuery
import com.example.CountryQuery


interface CountryRepository {
    suspend fun getCountries(): List<CountriesQuery.Country>
    suspend fun getCountry(code: String): CountryQuery.Country?
}