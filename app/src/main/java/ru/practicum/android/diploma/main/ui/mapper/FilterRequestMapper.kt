package ru.practicum.android.diploma.main.ui.mapper

import ru.practicum.android.diploma.filtration.domain.model.FilterStorage
import ru.practicum.android.diploma.main.domain.model.FilterRequestData
import ru.practicum.android.diploma.main.ui.model.FilterRequest

object FilterRequestMapper {
    fun toFilterRequest(data: FilterStorage): FilterRequest {
        return FilterRequest(
            areaId = if (data.areaId == "") null else data.areaId,
            industryId = if (data.industryId == "") null else data.industryId,
            salaryId = if (data.salaryId == "") null else data.salaryId,
            withSalary = if (data.withSalary == "") null else data.withSalary,
        )
    }

    fun toFilterRequestData(data: FilterRequest): FilterRequestData {
        return FilterRequestData(
            areaId = data.areaId,
            industryId = data.industryId,
            salaryId = data.salaryId,
            withSalary = data.withSalary,
        )
    }
}
