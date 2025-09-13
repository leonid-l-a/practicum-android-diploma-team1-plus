package ru.practicum.android.diploma.filtration.domain.repository

import ru.practicum.android.diploma.core.data.dto.FilterAreasResponse

interface AreaRepository {
    suspend fun getAreas(): FilterAreasResponse
}
