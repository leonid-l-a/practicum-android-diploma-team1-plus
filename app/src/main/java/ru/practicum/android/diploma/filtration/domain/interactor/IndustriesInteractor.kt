package ru.practicum.android.diploma.filtration.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filtration.domain.model.Industries
import ru.practicum.android.diploma.filtration.domain.state.Resource

interface IndustriesInteractor {
    suspend fun getIndustries(): Flow<Resource<Industries>>
}
