package ru.practicum.android.diploma.core.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.core.domain.AppStorageInteractor
import ru.practicum.android.diploma.core.domain.impl.AppStorageInteractorImpl

val coreInteractorModule = module {
    single<AppStorageInteractor> {
        AppStorageInteractorImpl(get())
    }
}
