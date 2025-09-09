package ru.practicum.android.diploma.vacancy.data.repository

import android.content.Intent
import androidx.core.net.toUri
import ru.practicum.android.diploma.vacancy.domain.repository.ShareVacancyDetailRepository

class ShareVacancyDetailRepositoryImplementation() : ShareVacancyDetailRepository {
    override fun shareVacancyWithMessenger(vacancyUrl: String): Intent {
        return Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                vacancyUrl
            )
            type = "text/plain"
        }, null)
    }

    override fun writeWithMail(mail: String): Intent {
        return Intent.createChooser(Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
        }, null)
    }

    override fun callWithPhone(phone: String): Intent {
        return Intent.createChooser(Intent(Intent.ACTION_DIAL).apply {
            data = "tel:$phone".toUri()
        }, null)
    }

}
