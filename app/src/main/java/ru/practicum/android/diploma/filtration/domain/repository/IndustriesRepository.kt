package ru.practicum.android.diploma.filtration.domain.repository

import ru.practicum.android.diploma.core.data.dto.FilterIndustryResponse

interface IndustriesRepository {
    suspend fun getIndustries(): FilterIndustryResponse
}
