package ru.practicum.android.diploma.main.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.interactor.impl.SearchVacancyInteractorImpl

val mainInteractorModule = module {
    single<SearchVacancyInteractor> {
        SearchVacancyInteractorImpl(get())
    }
}
