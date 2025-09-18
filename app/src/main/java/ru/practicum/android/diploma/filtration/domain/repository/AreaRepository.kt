package ru.practicum.android.diploma.filtration.domain.repository

import ru.practicum.android.diploma.core.data.dto.FilterAreasResponse
import ru.practicum.android.diploma.filtration.domain.state.Result

interface AreaRepository {
    suspend fun getAreas(): Result<FilterAreasResponse>
}
