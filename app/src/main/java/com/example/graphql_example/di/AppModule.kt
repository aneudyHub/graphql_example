package com.example.graphql_example.di

import com.apollographql.apollo3.ApolloClient
import com.example.graphql_example.data.CountryRepository
import com.example.graphql_example.data.CountryRepositoryImpl
import com.example.graphql_example.domain.GetCountriesListUseCase
import com.example.graphql_example.domain.GetCountryByCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun providesCountryRepository(apolloClient: ApolloClient): CountryRepository{
        return CountryRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun providesGetCountriesListUseCase(countryRepository: CountryRepository): GetCountriesListUseCase{
        return GetCountriesListUseCase(countryRepository)
    }

    @Provides
    @Singleton
    fun provideGetCountryByCodeUseCase(countryRepository: CountryRepository): GetCountryByCodeUseCase{
        return GetCountryByCodeUseCase(countryRepository)
    }
}