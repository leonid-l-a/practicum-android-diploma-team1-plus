package ru.practicum.android.diploma.main.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.state.Resource

interface SearchVacancyRepository {
    fun searchVacancy(
        expression: String,
        page: Int
    ): Flow<Resource<VacancyMainData>>
}
