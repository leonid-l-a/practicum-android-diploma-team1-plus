package ru.practicum.android.diploma.vacancy.domain.interactor

import ru.practicum.android.diploma.vacancy.domain.mapper.toDomain
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail
import ru.practicum.android.diploma.vacancy.domain.repository.VacancyDetailRepository

class VacancyDetailUseCaseImplementation(
    private val vacancyDetailRepository: VacancyDetailRepository
) : VacancyDetailUseCase {
    override suspend fun getVacancyDetail(id: String): VacancyDetail {
        val response = vacancyDetailRepository.getVacancyDetail(id).vacancyDetailDto
        return response.toDomain()
    }
}
