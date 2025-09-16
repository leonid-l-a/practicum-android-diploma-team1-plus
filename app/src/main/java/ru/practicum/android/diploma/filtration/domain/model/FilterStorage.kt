package ru.practicum.android.diploma.filtration.domain.model

data class FilterStorage(
    val areaId: String = "",
    val regionValue: String = "",
    val countryValue: String = "",
    val industryId: String = "",
    val industryValue: String = "",
    val salaryId: String = "",
    val salaryValue: String = "",
    val withSalary: String = "",
)
