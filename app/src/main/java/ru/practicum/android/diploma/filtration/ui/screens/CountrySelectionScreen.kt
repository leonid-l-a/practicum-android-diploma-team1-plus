package ru.practicum.android.diploma.filtration.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.FilterItem
import ru.practicum.android.diploma.filtration.ui.components.ShowErrorRegion
import ru.practicum.android.diploma.filtration.ui.components.TopBar
import ru.practicum.android.diploma.filtration.ui.state.CountrySelectionScreenState
import ru.practicum.android.diploma.filtration.ui.viewmodel.CountrySelectionViewModel
import ru.practicum.android.diploma.main.ui.components.CircularIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionScreen(
    viewModel: CountrySelectionViewModel,
    navController: NavController,
) {
    val screenState by viewModel.screenState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                stringResource(R.string.country_selector)
            ) { navController.popBackStack() }

        }
    ) { paddingValues ->

        when (val state = screenState) {
            is CountrySelectionScreenState.Data -> {
                val countries = state.countries
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues = paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(countries) { country ->
                        FilterItem(
                            labelText = country.name,
                            checked = false,
                            onClick = {
                                viewModel.onCountryClicked(country, navController)
                            }
                        ) { checked ->
                            val resId = if (checked) {
                                R.drawable.close_24
                            } else {
                                R.drawable.arrow_forward_24
                            }
                            Icon(
                                tint = MaterialTheme.colorScheme.onBackground,
                                painter = painterResource(
                                    id = resId
                                ),
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            CountrySelectionScreenState.Error -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShowErrorRegion()
                }
            }

            CountrySelectionScreenState.Loading -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularIndicator()
                }
            }
        }
    }
}
