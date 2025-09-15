package ru.practicum.android.diploma.filtration.ui.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import ru.practicum.android.diploma.filtration.ui.components.CountrySelector
import ru.practicum.android.diploma.filtration.ui.components.RegionSelector
import ru.practicum.android.diploma.filtration.ui.components.WorkPlaceSelector
import ru.practicum.android.diploma.filtration.ui.state.AreasScreenState
import ru.practicum.android.diploma.filtration.ui.viewmodel.AreasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkPlaceScreen(
    viewModel: AreasViewModel,
    navController: NavController
) {
    val workPlaceState by viewModel.screenState.collectAsState()
    when (workPlaceState) {
        AreasScreenState.CountryErrorState -> {
            CountrySelector(
                emptyList(),
                viewModel,
                onClick = { }
            )
        }

        is AreasScreenState.CountrySelection -> {
            val countries = (workPlaceState as AreasScreenState.CountrySelection).countries
            CountrySelector(
                countries,
                viewModel,
                { /** Лёня  сюда нужно передать метод записывающий страну в префсы*/ }
            )
        }

        is AreasScreenState.Main -> {
            val currentCountry = (workPlaceState as AreasScreenState.Main).selectedCountry?.id
            WorkPlaceSelector(
                onCountryClick = viewModel::openCountrySelection,
                onRegionClick = { viewModel.openRegionSelection(currentCountry) },
                viewModel,
                navController
            )
        }

        AreasScreenState.RegionEmptyState -> TODO()
        AreasScreenState.RegionErrorState -> {
            RegionSelector(
                emptyList(),
                viewModel,
                onClick = { },
                onSearchHandler = { },
                onResetRequest = TODO(),
            )
        }

        is AreasScreenState.RegionSelection -> {
            val regions = (workPlaceState as AreasScreenState.RegionSelection).regions
            RegionSelector(
                regions,
                viewModel,
                { /** Лёня  сюда нужно передать метод записывающий регион в префсы*/ },
                onSearchHandler = viewModel::searchRegions,
            )
        }
    }
}
