package ru.practicum.android.diploma.vacancy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCase
import ru.practicum.android.diploma.vacancy.ui.state.VacancyState
import java.io.IOException

class VacancyViewModel(
    val vacancyDetailUseCase: VacancyDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow<VacancyState>(VacancyState.Loading)

    val state: StateFlow<VacancyState> = _state

    private val vacancyId: String = checkNotNull(savedStateHandle["vacancyId"])

    init {
        viewModelScope.launch {
            try {
                val vacancyDetail = vacancyDetailUseCase.getVacancyDetail(vacancyId)
                _state.value = VacancyState.Success(vacancyDetail, false)
            } catch (e: IOException) {
                _state.value = VacancyState.ServerError
                Log.e("VacancyViewModel", "Server error", e)
            }
        }
    }
}
