package ru.practicum.android.diploma.vacancy.domain.interactor

import android.content.Intent
import ru.practicum.android.diploma.vacancy.domain.repository.ShareVacancyDetailRepository

class ShareVacancyDetailInteractorImplementation(
    private val shareVacancyDetailRepository: ShareVacancyDetailRepository
) : ShareVacancyDetailInteractor {
    override fun shareVacancyWithMessenger(vacancyUrl: String): Intent {
        return shareVacancyDetailRepository.shareVacancyWithMessenger(vacancyUrl)
    }

    override fun writeWithMail(mail: String): Intent {
        return shareVacancyDetailRepository.writeWithMail(mail)
    }

    override fun callWithPhone(phone: String): Intent {
        return shareVacancyDetailRepository.callWithPhone(phone)
    }
}
