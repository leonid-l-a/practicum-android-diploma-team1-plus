package ru.practicum.android.diploma.core.data.dto

data class VacancyRequest(
    val area: Int? = null,
    val industry: Int? = null,
    val text: String,
    val salary: Int? = null,
    val page: Int? = null,
    val onlyWithSalary: Boolean = false
)
