package ru.practicum.android.diploma.filtration.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filtration.domain.model.Industries
import ru.practicum.android.diploma.filtration.domain.repository.IndustriesRepository
import ru.practicum.android.diploma.filtration.domain.state.Resource

class IndustriesInteractorImpl(
    val industriesRepository: IndustriesRepository
) : IndustriesInteractor {
    override suspend fun getIndustries(): Flow<Resource<Industries>> {
        return industriesRepository.getIndustries()
    }
}
