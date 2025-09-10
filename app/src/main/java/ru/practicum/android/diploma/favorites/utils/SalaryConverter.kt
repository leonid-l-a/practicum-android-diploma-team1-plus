package ru.practicum.android.diploma.favorites.utils

private const val EMPTY_SALARY = "Зарплата не указана"

fun formatSalary(salaryFrom: Int?, salaryTo: Int?, currency: String?): String {
    val parts = mutableListOf<String>()

    if (salaryFrom != null) {
        parts.add("от $salaryFrom")
    }

    if (salaryTo != null) {
        parts.add("до $salaryTo")
    }

    if (!currency.isNullOrBlank()) {
        parts.add(currency)
    }

    return if (salaryFrom == null && salaryTo== null) {
        EMPTY_SALARY
    } else {
        parts.joinToString(" ")
    }
}

