package ru.practicum.android.diploma.filtration.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import ru.practicum.android.diploma.filtration.domain.interactor.GetAreasUseCase
import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region
import ru.practicum.android.diploma.filtration.domain.state.Result
import ru.practicum.android.diploma.filtration.ui.state.AreasScreenState

class AreasViewModel(
    private val getAreasUseCase: GetAreasUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<AreasScreenState>(AreasScreenState.Main())
    val screenState = _screenState.asStateFlow()

    private var cachedCountries: List<Country> = emptyList()
    private var cachedRegions: List<Region> = emptyList()
    private var cachedRegionsMap: Map<Int, List<Region>> = mapOf()
    private var loadJob: Job? = null
    private var hasLoaded: Boolean = false

    private fun loadData(isCountry: Boolean) {
        if (hasLoaded) return

        loadJob = viewModelScope.launch {
            try {
                when (val result = getAreasUseCase()) {
                    is Result.Success -> {
                        hasLoaded = true
                        val (countries, regionsMap) = result.data
                        cachedCountries = countries.toList()
                        cachedRegionsMap = regionsMap.mapValues { it.value.toList() }

                        if (isCountry) {
                            _screenState.value = AreasScreenState.CountrySelection(cachedCountries)
                        } else {
                            _screenState.value =
                                AreasScreenState.RegionSelection(regions = cachedRegionsMap.values.flatten())
                        }
                    }

                    is Result.Error -> {
                        if (isCountry) {
                            _screenState.value = AreasScreenState.CountryErrorState
                        } else {
                            _screenState.value = AreasScreenState.RegionErrorState
                        }
                    }
                }
            } catch (e: IOException) {
                Log.e("AreasViewModel", "Error loading areas", e)
                if (isCountry) {
                    _screenState.value = AreasScreenState.CountryErrorState
                } else {
                    _screenState.value = AreasScreenState.RegionErrorState
                }
            }
        }
    }

    fun openCountrySelection() {
        _screenState.value = AreasScreenState.CountrySelection(
            countries = cachedCountries
        )
    }

    fun openRegionSelection(countryId: Int? = null) {
        cachedRegions = countryId?.let { cachedRegionsMap[it] } ?: cachedRegionsMap.values.flatten()

        _screenState.value = if (cachedRegions.isEmpty()) {
            AreasScreenState.RegionEmptyState
        } else {
            AreasScreenState.RegionSelection(regions = cachedRegions)
        }
    }

    fun searchRegions(query: String) {
        val currentState = _screenState.value
        if (currentState is AreasScreenState.RegionSelection) {
            val filtered = if (query.isBlank()) {
                currentState.regions
            } else {
                currentState.regions.filter { it.name.contains(query, ignoreCase = true) }
            }
            if (filtered.isEmpty()) {
                _screenState.value = AreasScreenState.RegionEmptyState
            } else {
                _screenState.value = currentState.copy(
                    searchQuery = query,
                    regions = filtered
                )
            }
        }
    }

    fun goToWorkPlaceScreen() {
        val currentState = _screenState.value
        if (currentState !is AreasScreenState.Main) {
            _screenState.value = AreasScreenState.Main()
        }
    }

    fun selectCountry(country: Country) {
        _screenState.value = AreasScreenState.Main(
            selectedCountry = country
        )
        // тут сохранить в префсы
    }

    fun selectRegion(region: Region) {
        val country = cachedCountries.find { it.id == region.countryId }
        // И тут тоже
        _screenState.value = AreasScreenState.Main(
            selectedRegion = region,
            selectedCountry = country
        )
    }

    fun clearSearch() {
        val currentState = _screenState.value
        if (currentState is AreasScreenState.RegionSelection) {
            _screenState.value = currentState.copy(
                searchQuery = ""
            )
        }
    }
}
