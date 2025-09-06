package ru.practicum.android.diploma.main.ui.state

import org.koin.core.logger.MESSAGE
import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.core.data.dto.VacancyDetail

sealed class SearchState {

    data object Loading : SearchState()

    data class Content(
        val vacancy: List<VacancyDetail>
    ) : SearchState()

    data object Empty : SearchState()

    data class Error(
        val message: String
    ) : SearchState()

    data object Default: SearchState()

}
