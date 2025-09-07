package ru.practicum.android.diploma.vacancy.di.data

import org.koin.dsl.module
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.vacancy.data.repository.VacancyDetailRepositoryImplementation
import ru.practicum.android.diploma.vacancy.domain.repository.VacancyDetailRepository

val vacancyDataModule = module {
    single<VacancyDetailRepository> { VacancyDetailRepositoryImplementation(get<VacancyNetworkClient>()) }
}
