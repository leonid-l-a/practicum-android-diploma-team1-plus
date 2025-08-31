package ru.practicum.android.diploma.core.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.core.data.dto.FilterAreasResponce
import ru.practicum.android.diploma.core.data.dto.FilterIndustryResponce
import ru.practicum.android.diploma.core.data.dto.VacancyDetailResponce
import ru.practicum.android.diploma.core.data.dto.VacancyResponce

interface VacancyApiService {

    @GET("areas")
    suspend fun getFilterAreas(): FilterAreasResponce

    @GET("industries")
    suspend fun getFilterIndustries(): FilterIndustryResponce

    /**
     * Получает список вакансий с фильтрацией по переданным параметрам.
     *
     * @param params Map с параметрами запроса. Ключи должны соответствовать именам query-параметров API:
     *  - "text" (String) — поисковая строка,
     *  - "area" (Int) — ID региона (опционально),
     *  - "industry" (Int) — ID отрасли (опционально),
     *  - "salary" (Int) — минимальная зарплата (опционально),
     *  - "page" (Int) — номер страницы (опционально),
     *  - "only_with_salary" (Boolean) — фильтр только с зарплатой (по умолчанию false)
     *
     * @return [VacancyResponse] объект с результатами поиска вакансий.
     */
    @GET("vacancies")
    suspend fun getVacancies(
        @QueryMap params: @JvmSuppressWildcards Map<String, Any?>
    ): VacancyResponce

    @GET("vacancies/{id}")
    suspend fun getVacancy(
        @Path("id") id: Int
    ): VacancyDetailResponce
}
