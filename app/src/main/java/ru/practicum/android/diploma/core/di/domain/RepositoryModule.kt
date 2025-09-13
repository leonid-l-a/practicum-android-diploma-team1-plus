package ru.practicum.android.diploma.core.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.core.data.repository.AppRepositoryImpl
import ru.practicum.android.diploma.core.domain.repository.AppRepository

val coreRepositoryModule = module {
    single<AppRepository> {
        AppRepositoryImpl(get())
    }
}
