package ru.practicum.android.diploma.filtration.ui.state

import ru.practicum.android.diploma.filtration.domain.model.Region

sealed class RegionSelectionScreenState {
    object Loading : RegionSelectionScreenState()
    data class Data(
        val allRegions: List<Region>,
        val filteredRegions: List<Region>,
    ) : RegionSelectionScreenState()

    object Error : RegionSelectionScreenState()
    object EmptyResult : RegionSelectionScreenState()
}
