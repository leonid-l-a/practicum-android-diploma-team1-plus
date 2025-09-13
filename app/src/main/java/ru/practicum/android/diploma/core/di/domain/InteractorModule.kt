package ru.practicum.android.diploma.core.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.impl.AppInteractorImpl

val coreInteractorModule = module {
    single<AppInteractor> {
        AppInteractorImpl(get())
    }
}
