package ru.practicum.android.diploma.vacancy.domain.repository

import android.content.Intent

interface ShareVacancyDetailRepository {
    fun shareVacancyWithMessenger(vacancyUrl: String): Intent

    fun callWithPhone(phone: String): Intent

    fun writeWithMail(mail: String): Intent
}
