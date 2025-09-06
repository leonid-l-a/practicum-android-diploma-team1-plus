package ru.practicum.android.diploma.main.di.domain

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.main.data.repository.SearchVacancyRepositoryImpl
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository

val mainRepositoryModule = module {
    single<SearchVacancyRepository> {
        SearchVacancyRepositoryImpl(get(), get(), androidContext())
    }
}
