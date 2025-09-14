package ru.practicum.android.diploma.filtration.domain.interactor

import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail

interface IndustriesInteractor {
    suspend fun getIndustries(): List<IndustryDetail>
}
