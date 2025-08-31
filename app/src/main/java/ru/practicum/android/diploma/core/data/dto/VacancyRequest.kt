package ru.practicum.android.diploma.core.data.dto

data class VacancyRequest(
    val text: String,
    val area: Int? = null,
    val industry: Int? = null,
    val salary: Int? = null,
    val page: Int? = null,
    val onlyWithSalary: Boolean = false
) {
    fun toQueryMap(): Map<String, Any?> {
        return mapOf(
            "text" to text,
            "area" to area,
            "industry" to industry,
            "salary" to salary,
            "page" to page,
            "only_with_salary" to onlyWithSalary
        ).filterValues { it != null }
    }
}
