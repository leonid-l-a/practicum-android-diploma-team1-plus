package ru.practicum.android.diploma.main.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.state.Resource

interface SearchVacancyInteractor {
    fun searchVacancy(expression: String): Flow<Resource<VacancyMainData>>
}
