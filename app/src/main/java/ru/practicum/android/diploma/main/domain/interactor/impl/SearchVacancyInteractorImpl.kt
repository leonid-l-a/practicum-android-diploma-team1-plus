package ru.practicum.android.diploma.main.domain.interactor.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.model.Filter
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository
import ru.practicum.android.diploma.main.domain.state.Resource

class SearchVacancyInteractorImpl(
    val searchVacancyRepository: SearchVacancyRepository
) : SearchVacancyInteractor {
    override fun searchVacancy(
        expression: String,
        page: Int,
        filter: Filter
    ): Flow<Resource<VacancyMainData>> {
        return searchVacancyRepository.searchVacancy(expression, page, filter)
    }
}
