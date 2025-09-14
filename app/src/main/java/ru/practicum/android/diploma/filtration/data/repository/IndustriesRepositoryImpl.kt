package ru.practicum.android.diploma.filtration.data.repository

import ru.practicum.android.diploma.core.data.dto.FilterIndustryResponse
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.filtration.domain.repository.IndustriesRepository

class IndustriesRepositoryImpl(
    val networkClient: VacancyNetworkClient
) : IndustriesRepository {
    override suspend fun getIndustries(): FilterIndustryResponse {
        return networkClient.getFilterIndustries()
    }
}
