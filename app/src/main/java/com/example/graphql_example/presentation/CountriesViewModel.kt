package com.example.graphql_example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphql_example.domain.GetCountriesListUseCase
import com.example.graphql_example.domain.GetCountryByCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesListUseCase: GetCountriesListUseCase,
    private val getCountryByCodeUseCase: GetCountryByCodeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    countries = getCountriesListUseCase(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code : String){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedCountry = getCountryByCodeUseCase(code)
                )
            }
        }
    }

    fun dismissCountry(){
        _uiState.update {
            it.copy(
                selectedCountry = null
            )
        }
    }

    data class UiState(
        val countries: List<CountriesQuery.Country> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: CountryQuery.Country? = null
    )
}