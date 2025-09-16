package ru.practicum.android.diploma.main.domain.interactor.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.main.domain.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.model.FilterRequestData
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository
import ru.practicum.android.diploma.main.domain.state.Resource

class SearchVacancyInteractorImpl(
    val searchVacancyRepository: SearchVacancyRepository
) : SearchVacancyInteractor {
    override fun searchVacancy(
        expression: String,
        page: Int,
        filterMap: FilterRequestData
    ): Flow<Resource<VacancyMainData>> {
        return searchVacancyRepository.searchVacancy(expression, page, filterMap)
    }
}
