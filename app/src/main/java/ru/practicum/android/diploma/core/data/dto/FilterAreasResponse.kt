package ru.practicum.android.diploma.core.data.dto

data class FilterAreasResponse(
    val id: Int,
    val name: String,
    val parentId: Int,
    val areas: List<FilterAreasResponse>
) : Response()
