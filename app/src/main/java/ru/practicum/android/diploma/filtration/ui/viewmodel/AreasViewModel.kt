package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filtration.domain.interactor.GetAreasUseCase
import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region

class AreasViewModel(
    private val getAreasUseCase: GetAreasUseCase,
) : ViewModel() {

    // StateFlow потом эти можно убрать, для примера просто их оставил
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _regionsMap = MutableStateFlow<Map<Int,
        List<Region>>>(emptyMap())
    val regionsMap: StateFlow<Map<Int,
        List<Region>>> = _regionsMap

    init {
        viewModelScope.launch {
            val (countries, regionsMap) = getAreasUseCase()
            _countries.value = countries
            _regionsMap.value = regionsMap
        }
    }

    fun getRegions(countryId: Int): List<Region> {
        return _regionsMap.value[countryId].orEmpty()
    }
}
