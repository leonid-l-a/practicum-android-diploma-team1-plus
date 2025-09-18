package ru.practicum.android.diploma.main.domain.model

data class VacancyMainData(
    val found: Int,
    val pages: Int,
    val page: Int,
    val items: List<VacancyDetailMainData>
)
