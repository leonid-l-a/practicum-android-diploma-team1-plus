package ru.practicum.android.diploma.main.ui.state

import ru.practicum.android.diploma.main.ui.model.Vacancy

sealed class SearchState {

    data object Loading : SearchState()

    data class Content(
        val vacancy: List<Vacancy>
    ) : SearchState()

    data object Empty : SearchState()

    data class Error(
        val message: String
    ) : SearchState()

    data object Default : SearchState()

}
