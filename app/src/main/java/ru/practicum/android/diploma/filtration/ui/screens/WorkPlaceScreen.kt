package ru.practicum.android.diploma.filtration.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import ru.practicum.android.diploma.core.navigation.Screen
import ru.practicum.android.diploma.filtration.ui.components.WorkPlaceSelector
import ru.practicum.android.diploma.filtration.ui.state.WorkPlaceScreenState
import ru.practicum.android.diploma.filtration.ui.viewmodel.WorkPlaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkPlaceScreen(
    viewModel: WorkPlaceViewModel,
    navController: NavController
) {
    val workPlaceState by viewModel.screenState.collectAsState()


    val currentCountry = (workPlaceState as WorkPlaceScreenState.Data).selectedCountry
    WorkPlaceSelector(
        onCountryClick = {
            navController.navigate(Screen.CountrySelection.route)
        },
        onRegionClick = {
            navController.navigate(Screen.RegionSelection.regionSelectionRoute(currentCountry?.id))
        },
        navController = navController
    )
}
