package ru.practicum.android.diploma.filtration.domain.interactor

import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region
import ru.practicum.android.diploma.filtration.domain.state.Result

interface GetAreasUseCase {
    suspend operator fun invoke(): Result<Pair<List<Country>, Map<Int, List<Region>>>>
}
