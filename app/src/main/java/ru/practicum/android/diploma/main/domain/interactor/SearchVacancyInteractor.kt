package ru.practicum.android.diploma.main.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.main.domain.model.Resource

interface SearchVacancyInteractor {
    fun searchVacancy(expression: String): Flow<Resource<Vacancy>>
}
