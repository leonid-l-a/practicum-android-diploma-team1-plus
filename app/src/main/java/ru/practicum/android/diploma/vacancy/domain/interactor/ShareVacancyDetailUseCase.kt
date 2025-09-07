package ru.practicum.android.diploma.vacancy.domain.interactor

import android.content.Intent

interface ShareVacancyDetailUseCase {
    fun shareVacancy(vacancyUrl: String): Intent
}
