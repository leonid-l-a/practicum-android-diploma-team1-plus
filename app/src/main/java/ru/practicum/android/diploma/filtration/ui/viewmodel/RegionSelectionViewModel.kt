package ru.practicum.android.diploma.filtration.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import ru.practicum.android.diploma.filtration.domain.interactor.GetAreasUseCase
import ru.practicum.android.diploma.filtration.domain.model.Region
import ru.practicum.android.diploma.filtration.domain.state.Result
import ru.practicum.android.diploma.filtration.ui.state.RegionSelectionScreenState
import ru.practicum.android.diploma.util.DebounceUtil

class RegionSelectionViewModel(
    private val getAreasUseCase: GetAreasUseCase,
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<RegionSelectionScreenState>(RegionSelectionScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private var loadJob: Job? = null
    private var allRegions: List<Region> = emptyList()

    companion object {
        const val DEBOUNCE_DELAY = 1000L // Задержка в миллисекундах
    }

    private val searchDebounce = DebounceUtil(DEBOUNCE_DELAY, viewModelScope) // 1 секунда

    fun loadData(countryId: Int? = null) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            try {
                when (val result = getAreasUseCase()) {
                    is Result.Success -> {
                        val regionsMap = result.data.second
                        val fullList = regionsMap.values.flatten()

                        allRegions = if (countryId != null) {
                            fullList.filter { it.countryId == countryId }
                        } else {
                            fullList
                        }

                        _screenState.value = if (allRegions.isEmpty()) {
                            RegionSelectionScreenState.EmptyResult
                        } else {
                            RegionSelectionScreenState.Data(
                                allRegions = allRegions,
                                filteredRegions = allRegions
                            )
                        }
                    }
                    is Result.Error -> {
                        _screenState.value = RegionSelectionScreenState.Error
                    }
                }
            } catch (e: IOException) {
                Log.e("RegionsViewModel", "Error loading areas", e)
                _screenState.value = RegionSelectionScreenState.Error
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchDebounce.invoke {
            filterRegions(query)
        }
    }

    private fun filterRegions(query: String) {
        if (query.isBlank()) {
            _screenState.value = RegionSelectionScreenState.Data(
                allRegions = allRegions,
                filteredRegions = allRegions
            )
            return
        }

        val filtered = allRegions.filter { it.name.contains(query, ignoreCase = true) }

        _screenState.value = if (filtered.isEmpty()) {
            RegionSelectionScreenState.EmptyResult
        } else {
            RegionSelectionScreenState.Data(
                allRegions = allRegions,
                filteredRegions = filtered
            )
        }
    }

    fun onCountryClicked(region: Region, navController: NavController) {
        // сохранить выбранный  регион в SharedPreferences
        navController.popBackStack()
    }
}
