package com.example.graphql_example.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.CountriesQuery
import com.example.graphql_example.presentation.CountriesViewModel


@Composable
fun CountriesScreen(
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()


    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {  }
                            .padding(16.dp)
                    )
                }
            }
        }
    }


}

@Composable
fun CountryItem(
    country: CountriesQuery.Country,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = country.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital?:"", fontSize = 24.sp)
        }
    }
}