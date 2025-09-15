package ru.practicum.android.diploma.filtration.data.mapper

import ru.practicum.android.diploma.core.data.dto.FilterIndustryResponse
import ru.practicum.android.diploma.filtration.domain.model.Industries
import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail
import ru.practicum.android.diploma.filtration.domain.model.StorageResultCode

object IndustriesMapper {
    fun toIndustries(data: FilterIndustryResponse): Industries {
        return Industries(
            items = toIndustryDetail(data),
            resultCode = StorageResultCode.SUCCESS
        )
    }
    fun toIndustryDetail(data: FilterIndustryResponse): List<IndustryDetail> {
        return data.industries.map { industry ->
            IndustryDetail(
                id = industry.id,
                name = industry.name
            )
        }
    }
}
