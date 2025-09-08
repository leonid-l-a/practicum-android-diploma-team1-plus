package ru.practicum.android.diploma.vacancy.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.vacancy.domain.interactor.ShareVacancyDetailUseCase
import ru.practicum.android.diploma.vacancy.domain.interactor.ShareVacancyDetailUseCaseImplementation
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCase
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCaseImplementation
import ru.practicum.android.diploma.vacancy.domain.repository.ShareVacancyDetailRepository
import ru.practicum.android.diploma.vacancy.domain.repository.VacancyDetailRepository

val vacancyInteractorModule = module {
    factory<VacancyDetailUseCase> {
        VacancyDetailUseCaseImplementation(get<VacancyDetailRepository>())
    }

    factory<ShareVacancyDetailUseCase> {
        ShareVacancyDetailUseCaseImplementation(get<ShareVacancyDetailRepository>())
    }
}
