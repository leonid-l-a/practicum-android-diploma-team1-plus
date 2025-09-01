package ru.practicum.android.diploma.core.data.utils

import ru.practicum.android.diploma.core.data.dto.VacancyRequest

/**
 * Преобразует [VacancyRequest] в Map для передачи в качестве query-параметров Retrofit.
 *
 * В map попадут только непустые значения полей:
 * - "text" (String?) — поисковая строка.
 * - "area" (Int?) — ID региона.
 * - "industry" (Int?) — ID отрасли.
 * - "salary" (Int?) — минимальная зарплата.
 * - "page" (Int?) — номер страницы.
 * - "only_with_salary" (Boolean) — фильтр только с зарплатой.
 *
 * @return [Map] с ключами, соответствующими query-параметрам API и их значениями.
 *
 * @sample
 * val request = VacancyRequest(text = "Kotlin", area = 1)
 * val queryMap = request.toQueryMap()
 */
fun VacancyRequest.toQueryMap(): Map<String, Any?> {
    val map = mutableMapOf<String, Any?>()
    text.let { map["text"] = it }
    area?.let { map["area"] = it }
    industry?.let { map["industry"] = it }
    salary?.let { map["salary"] = it }
    page?.let { map["page"] = it }
    map["only_with_salary"] = onlyWithSalary
    return map
}
