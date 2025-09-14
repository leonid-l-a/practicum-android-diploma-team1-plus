package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filtration.domain.interactor.IndustriesInteractor
import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail

class IndustriesViewModel(
    val industriesInteractor: IndustriesInteractor
) : ViewModel() {
    private val _industries = MutableStateFlow<List<IndustryDetail>>(emptyList())
    val industries = _industries.asStateFlow()

    init {
        viewModelScope.launch {
            val data = industriesInteractor.getIndustries()
            _industries.value = data
        }
    }
}
