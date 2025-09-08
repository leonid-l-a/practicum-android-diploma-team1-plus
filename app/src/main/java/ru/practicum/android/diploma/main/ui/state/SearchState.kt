package ru.practicum.android.diploma.main.ui.state

import ru.practicum.android.diploma.main.ui.model.Vacancy

sealed class SearchState {

    data object Loading : SearchState()

    data class Content(
        val vacancy: List<Vacancy>,
        val countVacancy: Int? = null
    ) : SearchState()

    data object Empty : SearchState()

    object Error : SearchState()

    data object Default : SearchState()

}
