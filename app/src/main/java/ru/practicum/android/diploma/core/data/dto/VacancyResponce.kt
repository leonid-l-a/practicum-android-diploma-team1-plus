package ru.practicum.android.diploma.core.data.dto

data class VacancyResponce(
    val found: Int,
    val pages: Int,
    val page: Int,
    val vacancies: List<VacancyDetailResponce>
) : Responce()
