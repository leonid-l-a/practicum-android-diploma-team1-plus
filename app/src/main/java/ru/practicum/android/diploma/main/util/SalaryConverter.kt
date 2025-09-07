package ru.practicum.android.diploma.main.util

import ru.practicum.android.diploma.main.data.model.SalaryMainData

private const val SALARY_FROM_TO = "от %1\$s и до %2\$s"
private const val SALARY_FROM = "от %s"
private const val SALARY_TO = "до %s"
private const val EMPTY_SALARY = "Зарплата не указана"

fun SalaryMainData.getFormatSalary(): String {
    val formattedFrom = from?.let { formatNumber(it) }
    val formattedTo = to?.let { formatNumber(it) }
    val currency = currency?.let { " $it" } ?: ""

    return when {
        from != null && to != null -> String.format(SALARY_FROM_TO, formattedFrom, formattedTo) + currency
        from != null -> String.format(SALARY_FROM, formattedFrom) + currency
        to != null -> String.format(SALARY_TO, formattedTo) + currency
        else -> EMPTY_SALARY
    }
}

private fun formatNumber(number: Int): String {
    return "%,d".format(number).replace(',', ' ')
}
