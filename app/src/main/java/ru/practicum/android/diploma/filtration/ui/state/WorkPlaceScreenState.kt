package ru.practicum.android.diploma.filtration.ui.state

import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region

sealed class WorkPlaceScreenState {
    data class Data(
        val selectedCountry: Country? = null,
        val selectedRegion: Region? = null
    ) : WorkPlaceScreenState()
}
