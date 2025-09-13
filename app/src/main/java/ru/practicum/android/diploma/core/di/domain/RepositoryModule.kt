package ru.practicum.android.diploma.core.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.core.data.repository.AppStorageRepositoryImpl
import ru.practicum.android.diploma.core.domain.repository.AppStorageRepository

val coreRepositoryModule = module {
    single<AppStorageRepository> {
        AppStorageRepositoryImpl(get())
    }
}
