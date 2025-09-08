package ru.practicum.android.diploma.vacancy.domain.interactor

import android.content.Intent
import ru.practicum.android.diploma.vacancy.domain.repository.ShareVacancyDetailRepository

class ShareVacancyDetailUseCaseImplementation(
    private val shareVacancyDetailRepository: ShareVacancyDetailRepository
) : ShareVacancyDetailUseCase {
    override fun shareVacancy(vacancyUrl: String): Intent {
        return shareVacancyDetailRepository.shareVacancy(vacancyUrl)
    }
}
