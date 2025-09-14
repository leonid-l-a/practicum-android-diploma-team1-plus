package ru.practicum.android.diploma.filtration.domain.mapper

import ru.practicum.android.diploma.core.data.dto.FilterIndustryResponse
import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail

object IndustriesMapper {
    fun toIndustryDetail(data: FilterIndustryResponse): List<IndustryDetail> {
        return data.industries.map { industry ->
            IndustryDetail(
                id = industry.id,
                name = industry.name
            )
        }
    }
}
