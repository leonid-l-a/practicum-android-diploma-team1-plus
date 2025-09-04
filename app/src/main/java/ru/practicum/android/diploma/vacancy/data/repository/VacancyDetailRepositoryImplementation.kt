package ru.practicum.android.diploma.vacancy.data.repository

import ru.practicum.android.diploma.core.data.dto.VacancyDetailResponse
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.vacancy.domain.repository.VacancyDetailRepository

class VacancyDetailRepositoryImplementation(private val vacancyNetworkClient: VacancyNetworkClient) : VacancyDetailRepository {
    override suspend fun getVacancyDetail(id: String): VacancyDetailResponse {
        return vacancyNetworkClient.getVacancy(id)
    }

}
