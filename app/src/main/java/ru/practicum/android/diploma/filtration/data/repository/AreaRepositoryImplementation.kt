package ru.practicum.android.diploma.filtration.data.repository

import ru.practicum.android.diploma.core.data.dto.FilterAreasResponse
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.filtration.domain.repository.AreaRepository

class AreaRepositoryImplementation(
    private val vacancyNetworkClient: VacancyNetworkClient,
) : AreaRepository {
    override suspend fun getAreas(): FilterAreasResponse {
        return vacancyNetworkClient.getFilterAreas()
    }
}
