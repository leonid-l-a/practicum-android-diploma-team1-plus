package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.ui.state.WorkPlaceScreenState

class WorkPlaceViewModel(
    private val appInteractor: AppInteractor,
) : ViewModel() {

    private val _countryName = MutableStateFlow("")
    val countryName = _countryName.asStateFlow()

    private val _regionName = MutableStateFlow("")
    val regionName = _regionName.asStateFlow()

    private val _areaId = MutableStateFlow<String?>(null)
    val areaId = _areaId.asStateFlow()

    init {
        viewModelScope.launch {
            appInteractor.getAllDataWithNames().collect {
                val country = appInteractor.getData(StorageKey.COUNTRY_NAME_KEY) ?: ""
                val region = appInteractor.getData(StorageKey.REGION_NAME_KEY) ?: ""
                val area = appInteractor.getData(StorageKey.AREA_ID_KEY)

                _countryName.value = country
                _regionName.value = region
                _areaId.value = area
            }

        }
    }

    fun clearCountry() {
        appInteractor.saveData(StorageKey.COUNTRY_NAME_KEY, "")
    }

    fun clearRegion() {
        appInteractor.saveData(StorageKey.REGION_NAME_KEY, "")
    }
}
