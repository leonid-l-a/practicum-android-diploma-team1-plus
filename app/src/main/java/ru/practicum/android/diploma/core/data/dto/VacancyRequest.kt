package ru.practicum.android.diploma.core.data.dto

data class VacancyRequest(
    val text: String,
    val area: Int? = null,
    val industry: Int? = null,
    val salary: Int? = null,
    val page: Int? = null,
    val onlyWithSalary: Boolean = false
)
