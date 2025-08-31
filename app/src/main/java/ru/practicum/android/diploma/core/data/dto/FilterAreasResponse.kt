package ru.practicum.android.diploma.core.data.dto

import ru.practicum.android.diploma.core.data.dto.vacancyDetails.FilterArea

data class FilterAreasResponse(
    val areas: List<FilterArea>
) : Response()
