package ru.practicum.android.diploma.main.data.model

data class VacancyDetailMainData(
    val id: String,
    val name: String,
    val salary: SalaryMainData,
    val employer: EmployerMainData,
    val city: String
)
