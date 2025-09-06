package ru.practicum.android.diploma.main.domain.interactor.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.model.Resource
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository

class SearchVacancyInteractorImpl(
    val searchVacancyRepository: SearchVacancyRepository
): SearchVacancyInteractor {
    override fun searchVacancy(expression: String): Flow<Resource<Vacancy>> {
        val vacancyRequest = VacancyRequest(
            text = expression
        )
        return searchVacancyRepository.searchVacancy(vacancyRequest)
    }
}
