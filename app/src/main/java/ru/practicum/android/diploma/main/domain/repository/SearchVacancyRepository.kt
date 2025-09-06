package ru.practicum.android.diploma.main.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import ru.practicum.android.diploma.main.domain.model.Resource

interface SearchVacancyRepository {
    fun searchVacancy(vacancyRequest: VacancyRequest): Flow<Resource<Vacancy>>
}
