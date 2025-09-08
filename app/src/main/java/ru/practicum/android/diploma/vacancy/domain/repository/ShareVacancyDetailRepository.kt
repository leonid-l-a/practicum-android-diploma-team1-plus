package ru.practicum.android.diploma.vacancy.domain.repository

import android.content.Intent

interface ShareVacancyDetailRepository {
    fun shareVacancy(vacancyUrl: String): Intent
}
