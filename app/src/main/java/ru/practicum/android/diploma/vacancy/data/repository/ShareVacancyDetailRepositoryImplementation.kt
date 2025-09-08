package ru.practicum.android.diploma.vacancy.data.repository

import android.content.Context
import android.content.Intent
import ru.practicum.android.diploma.vacancy.domain.repository.ShareVacancyDetailRepository

class ShareVacancyDetailRepositoryImplementation(private val context: Context) : ShareVacancyDetailRepository {
    override fun shareVacancy(vacancyUrl: String): Intent {
        return Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                vacancyUrl
            )
            // Я вообще не уверен, что здесь нужно использовать text/plain, но пока надеюсь, что так правильно
            type = "text/plain"
        }, null)
    }
}
