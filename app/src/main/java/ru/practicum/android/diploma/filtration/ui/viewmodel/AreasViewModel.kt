package ru.practicum.android.diploma.filtration.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _screenState = MutableStateFlow<AreasScreenState>(AreasScreenState.Main(isLoading = true))
    val screenState: StateFlow<AreasScreenState> = _screenState

    private var cachedCountries: List<Country> = emptyList()
    private var cachedRegionsMap: Map<Int, List<Region>> = emptyMap()
    private var loadJob: Job? = null

    init {
        loadData()
    }

    private fun setLoading(loading: Boolean) {
        val current = _screenState.value
        if (current is AreasScreenState.Main) {
            _screenState.value = current.copy(isLoading = loading)
        } else {
            _screenState.value = AreasScreenState.Main(isLoading = loading)
        }
    }

    private fun loadData() {
        if (loadJob?.isActive == true) return

        loadJob = viewModelScope.launch {
            setLoading(true)
            try {
                when (val result = getAreasUseCase()) {
                    is Result.Success -> {
                        val (countries, regionsMap) = result.data
                        cachedCountries = countries.toList()
                        cachedRegionsMap = regionsMap.mapValues { it.value.toList() }

                        _screenState.value = AreasScreenState.Main(isLoading = false)
                    }

                    is Result.Error -> {
                        retry()
                    }
                }
            } catch (e: IOException) {
                setLoading(false)
                Log.e("AreasViewModel", "Error loading areas", e)

            }
        }
    }

    fun retry() {
        val current = _screenState.value
        if (current is AreasScreenState.Main) {
            setLoading(true)
            loadData()
        } else {
            loadData()
        }
    }

    fun openCountrySelection() {
        _screenState.value = AreasScreenState.CountrySelection(
            countries = cachedCountries
        )
    }

    fun openRegionSelection(countryId: Int) {
        val regions = cachedRegionsMap[countryId].orEmpty()
        _screenState.value = AreasScreenState.RegionSelection(
            countryId = countryId,
            allRegions = regions,
            filteredRegions = regions,
            searchQuery = "",
            hasError = false,
            noResults = regions.isEmpty()
        )
    }

    fun selectCountry(country: Country) {
        _screenState.value = AreasScreenState.Main(
            isLoading = false,
            selectedCountry = country
        )
        // тут сохранить в префсы
    }

    fun selectRegion(region: Region) {
        val country = cachedCountries.find { it.id == region.countryId }
        // И тут тоже
        _screenState.value = AreasScreenState.Main(
            isLoading = false,
            selectedRegion = region,
            selectedCountry = country
        )
    }

    fun searchRegions(query: String) {
        val currentState = _screenState.value
        if (currentState is AreasScreenState.RegionSelection) {
            val filtered = if (query.isBlank()) {
                currentState.allRegions
            } else {
                currentState.allRegions.filter { it.name.contains(query, ignoreCase = true) }
            }
            _screenState.value = currentState.copy(
                searchQuery = query,
                filteredRegions = filtered,
                noResults = filtered.isEmpty() && query.isNotBlank()
            )
        }
    }

    fun clearSearch() {
        val currentState = _screenState.value
        if (currentState is AreasScreenState.RegionSelection) {
            _screenState.value = currentState.copy(
                searchQuery = "",
                filteredRegions = currentState.allRegions,
                noResults = currentState.allRegions.isEmpty()
            )
        }
    }
}
