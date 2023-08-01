package com.example.graphql_example.domain

import com.example.CountriesQuery
import com.example.graphql_example.data.CountryRepository
import javax.inject.Inject

class GetCountriesListUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(): List<CountriesQuery.Country>{
        return countryRepository.getCountries()
    }
}