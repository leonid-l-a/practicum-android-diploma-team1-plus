package ru.practicum.android.diploma.main.util

import ru.practicum.android.diploma.main.data.model.SalaryMainData

object Templates {
    const val SALARY_FROM_TO = "от %1\$s и до %2\$s"
    const val SALARY_FROM = "от %s"
    const val SALARY_TO = "до %s"
    const val EMPTY_SALARY = "Зарплата не указана"
}

fun SalaryMainData.getFormatSalary(): String {
    val formattedFrom = from?.let { formatNumber(it) }
    val formattedTo = to?.let { formatNumber(it) }
    val currency = currency?.let { " $it" } ?: ""

    return when {
        from != null && to != null -> String.format(Templates.SALARY_FROM_TO, formattedFrom, formattedTo) + currency
        from != null -> String.format(Templates.SALARY_FROM, formattedFrom) + currency
        to != null -> String.format(Templates.SALARY_TO, formattedTo) + currency
        else -> Templates.EMPTY_SALARY
    }
}


private fun formatNumber(number: Int): String {
    return "%,d".format(number).replace(',', ' ')
}
