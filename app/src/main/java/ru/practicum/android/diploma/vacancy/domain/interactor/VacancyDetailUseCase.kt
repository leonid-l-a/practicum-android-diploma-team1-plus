package ru.practicum.android.diploma.vacancy.domain.interactor

import ru.practicum.android.diploma.core.data.dto.VacancyDetail

interface VacancyDetailUseCase {
    suspend fun getVacancyDetail(id: String): VacancyDetail
}
