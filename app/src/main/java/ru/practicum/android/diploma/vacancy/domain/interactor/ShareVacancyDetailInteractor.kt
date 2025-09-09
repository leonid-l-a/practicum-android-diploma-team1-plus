package ru.practicum.android.diploma.vacancy.domain.interactor

import android.content.Intent

interface ShareVacancyDetailInteractor {
    fun shareVacancyWithMessenger(vacancyUrl: String): Intent

    fun writeWithMail(mail: String): Intent

    fun callWithPhone(phone: String): Intent
}
