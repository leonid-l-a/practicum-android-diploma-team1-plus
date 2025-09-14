package ru.practicum.android.diploma.filtration.domain.interactor

import ru.practicum.android.diploma.filtration.domain.mapper.IndustriesMapper
import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail
import ru.practicum.android.diploma.filtration.domain.repository.IndustriesRepository

class IndustriesInteractorImpl(
    val industriesRepository: IndustriesRepository
) : IndustriesInteractor {
    override suspend fun getIndustries(): List<IndustryDetail> {
        val filterIndustryResponse = industriesRepository.getIndustries()
        return IndustriesMapper.toIndustryDetail(filterIndustryResponse)
    }
}
