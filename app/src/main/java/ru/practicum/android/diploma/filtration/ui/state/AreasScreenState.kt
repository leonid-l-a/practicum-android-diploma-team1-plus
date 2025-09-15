package ru.practicum.android.diploma.filtration.ui.state

import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region

sealed class AreasScreenState {
    data class Main(
        val selectedCountry: Country? = null,
        val selectedRegion: Region? = null
    ) : AreasScreenState()

    data class CountrySelection(
        val countries: List<Country>,
    ) : AreasScreenState()

    object CountryErrorState : AreasScreenState()

    data class RegionSelection(
        val countryId: Int? = null,
        val regions: List<Region>,
        val searchQuery: String = ""
    ) : AreasScreenState()

    object RegionErrorState : AreasScreenState()

    object RegionEmptyState : AreasScreenState()
}
