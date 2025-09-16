package ru.practicum.android.diploma.filtration.ui.state

import ru.practicum.android.diploma.filtration.domain.model.Country

sealed class CountrySelectionScreenState {
    object Loading : CountrySelectionScreenState()
    data class Data(val countries: List<Country>) : CountrySelectionScreenState()
    object Error : CountrySelectionScreenState()
}
