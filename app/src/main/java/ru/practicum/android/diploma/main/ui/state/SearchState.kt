package ru.practicum.android.diploma.main.ui.state

import ru.practicum.android.diploma.main.data.model.VacancyDetailMainData

sealed class SearchState {

    data object Loading : SearchState()

    data class Content(
        val vacancy: List<VacancyDetailMainData>
    ) : SearchState()

    data object Empty : SearchState()

    data class Error(
        val message: String
    ) : SearchState()

    data object Default : SearchState()

}
