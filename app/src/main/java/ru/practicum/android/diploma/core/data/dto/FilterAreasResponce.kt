package ru.practicum.android.diploma.core.data.dto

import ru.practicum.android.diploma.core.data.dto.vacancy_details.FilterArea

data class FilterAreasResponce(
    val areas: List<FilterArea>
) : Responce()
