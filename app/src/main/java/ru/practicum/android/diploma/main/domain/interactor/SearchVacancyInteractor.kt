package ru.practicum.android.diploma.main.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.model.Filter
import ru.practicum.android.diploma.main.domain.state.Resource

interface SearchVacancyInteractor {
    fun searchVacancy(
        expression: String,
        page: Int,
        filter: Filter
    ): Flow<Resource<VacancyMainData>>
}
