package ru.practicum.android.diploma.core.data.dto

import ru.practicum.android.diploma.core.data.dto.vacancydetails.FilterArea

data class FilterAreasResponse(
    val areas: List<FilterArea>
) : Response()
