package ru.practicum.android.diploma.vacancy.domain.repository

import ru.practicum.android.diploma.core.data.dto.VacancyDetailResponse

interface VacancyDetailRepository {
    suspend fun getVacancyDetail(id: String): VacancyDetailResponse
}
