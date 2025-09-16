package ru.practicum.android.diploma.main.domain.model

data class VacancyDetailMainData(
    val id: String,
    val name: String,
    val salary: SalaryMainData,
    val employer: EmployerMainData,
    val city: String
)
