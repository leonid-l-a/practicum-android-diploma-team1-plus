package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.filtration.ui.state.WorkPlaceScreenState

class WorkPlaceViewModel(
) : ViewModel() {

    private val _screenState = MutableStateFlow<WorkPlaceScreenState>(WorkPlaceScreenState.Data())
    val screenState = _screenState.asStateFlow()
    init {
        // получить данные из префсов
    }

}
