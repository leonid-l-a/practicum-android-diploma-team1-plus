package ru.practicum.android.diploma.filtration.ui.state

import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region

sealed class AreasScreenState {
    data class Main(
        val selectedCountry: Country? = null,
        val selectedRegion: Region? = null,
        val isLoading: Boolean = true
    ) : AreasScreenState()

    data class CountrySelection(
        val countries: List<Country> = emptyList(),
    ) : AreasScreenState()

    data class RegionSelection(
        val countryId: Int,
        val allRegions: List<Region> = emptyList(),
        val filteredRegions: List<Region> = emptyList(),
        val searchQuery: String = "",
        val hasError: Boolean = false,
        val noResults: Boolean = false
    ) : AreasScreenState()
}
