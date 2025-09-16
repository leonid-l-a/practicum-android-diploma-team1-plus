package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.domain.model.FilterStorage

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
}
