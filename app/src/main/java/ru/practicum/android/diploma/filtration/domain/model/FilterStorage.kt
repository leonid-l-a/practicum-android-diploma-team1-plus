package ru.practicum.android.diploma.filtration.domain.model

data class FilterStorage(
    val areaId: String = "",
    val areaValue: String = "",
    val industryId: String = "",
    val industryValue: String = "",
    val salaryId: String = "",
    val salaryValue: String = "",
    val withSalary: String = "",
)

fun FilterStorage.hasActiveFilters(): Boolean {
    return areaValue.isNotEmpty() ||
        salaryValue.isNotEmpty() ||
        industryValue.isNotEmpty() ||
        withSalary.isNotEmpty()
}
