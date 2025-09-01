package ru.practicum.android.diploma.core.data.dto

data class VacancyResponse(
    val found: Int,
    val pages: Int,
    val page: Int,
    val vacancies: List<VacancyDetailResponse>
) : Response()
