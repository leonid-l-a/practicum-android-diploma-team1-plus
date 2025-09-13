package ru.practicum.android.diploma.filtration.domain.interactor

import ru.practicum.android.diploma.filtration.domain.mapper.AreaMapper
import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region
import ru.practicum.android.diploma.filtration.domain.repository.AreaRepository

class GetAreasUseCaseImplementation(
    private val repository: AreaRepository,
    private val mapper: AreaMapper,
) : GetAreasUseCase {
    override suspend fun invoke(): Pair<List<Country>,
        Map<Int, List<Region>>> {
        val response = repository.getAreas()
        return mapper.toCountriesAndRegions(response)
    }
}
