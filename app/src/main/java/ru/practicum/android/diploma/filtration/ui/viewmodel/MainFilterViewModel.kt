package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.domain.model.FilterStorage
import ru.practicum.android.diploma.filtration.ui.model.data.Industry

class MainFilterViewModel(
    val appInteractor: AppInteractor
) : ViewModel() {
    private val _stateFilter = MutableStateFlow(value = FilterStorage())
    val stateFilter = _stateFilter.asStateFlow()

    init {
        viewModelScope.launch {
            appInteractor.getAllDataWithNames().collect {
                _stateFilter.value = it
            }
        }
    }

    fun clearByKey(key: StorageKey) {
        viewModelScope.launch {
            appInteractor.clearByKey(key = key).collect {
                _stateFilter.value = it
            }
        }
    }

    fun saveIndustry(industry: Industry?) {
        industry?.let {
            appInteractor.saveData(StorageKey.INDUSTRY_ID_KEY, industry.id)
            appInteractor.saveData(StorageKey.INDUSTRY_NAME_KEY, industry.name)
            val tmp = _stateFilter.value.copy(
                industryId = industry.id.toString(),
                industryValue = industry.name
            )
            _stateFilter.value = tmp
        }
    }
}
