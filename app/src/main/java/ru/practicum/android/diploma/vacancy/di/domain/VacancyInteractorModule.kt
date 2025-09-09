package ru.practicum.android.diploma.vacancy.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.vacancy.domain.interactor.ShareVacancyDetailInteractor
import ru.practicum.android.diploma.vacancy.domain.interactor.ShareVacancyDetailInteractorImplementation
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCase
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCaseImplementation
import ru.practicum.android.diploma.vacancy.domain.repository.ShareVacancyDetailRepository
import ru.practicum.android.diploma.vacancy.domain.repository.VacancyDetailRepository

val vacancyInteractorModule = module {
    factory<VacancyDetailUseCase> {
        VacancyDetailUseCaseImplementation(get<VacancyDetailRepository>())
    }

    factory<ShareVacancyDetailInteractor> {
        ShareVacancyDetailInteractorImplementation(get<ShareVacancyDetailRepository>())
    }
}
