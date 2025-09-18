package ru.practicum.android.diploma.filtration.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filtration.domain.model.Industries
import ru.practicum.android.diploma.filtration.domain.state.Resource

interface IndustriesRepository {
    suspend fun getIndustries(): Flow<Resource<Industries>>
}
