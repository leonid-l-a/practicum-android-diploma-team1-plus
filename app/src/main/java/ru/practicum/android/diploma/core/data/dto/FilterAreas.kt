package ru.practicum.android.diploma.core.data.dto

data class FilterAreas(
    val id: Int,
    val name: String,
    val parentId: Int,
    val areas: List<FilterAreas>
)
