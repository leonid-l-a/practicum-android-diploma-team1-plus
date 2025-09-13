package ru.practicum.android.diploma.filtration.domain.interactor

import ru.practicum.android.diploma.filtration.domain.model.Country
import ru.practicum.android.diploma.filtration.domain.model.Region

interface GetAreasUseCase {
    suspend operator fun invoke(): Pair<List<Country>, Map<Int, List<Region>>>
}
