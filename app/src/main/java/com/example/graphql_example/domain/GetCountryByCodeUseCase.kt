package com.example.graphql_example.domain

import com.example.CountryQuery
import com.example.graphql_example.data.CountryRepository
import javax.inject.Inject

class GetCountryByCodeUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(code: String): CountryQuery.Country? {
        return countryRepository.getCountry(code)
    }
}