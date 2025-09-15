package ru.practicum.android.diploma.filtration.data.repository

import ru.practicum.android.diploma.core.data.dto.FilterAreasResponse
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.filtration.domain.repository.AreaRepository
import ru.practicum.android.diploma.filtration.domain.state.Result

class AreaRepositoryImplementation(
    private val vacancyNetworkClient: VacancyNetworkClient,
) : AreaRepository {
    override suspend fun getAreas(): Result<FilterAreasResponse> {
        val successResponseCode = 200
        val response = vacancyNetworkClient.getFilterAreas()
        return if (response.resultCode == successResponseCode) {
            Result.Success(response)
        } else {
            Result.Error("Ошибка сети (код: ${response.resultCode})")
        }
    }
}
