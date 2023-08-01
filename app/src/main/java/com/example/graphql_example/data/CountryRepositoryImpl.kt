package com.example.graphql_example.data

import com.apollographql.apollo3.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery

class CountryRepositoryImpl(
    private val apolloClient: ApolloClient
): CountryRepository {
    override suspend fun getCountries(): List<CountriesQuery.Country> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): CountryQuery.Country? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
    }

}