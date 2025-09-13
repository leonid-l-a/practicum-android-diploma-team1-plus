package ru.practicum.android.diploma.filtration.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.filtration.domain.interactor.GetAreasUseCase
import ru.practicum.android.diploma.filtration.domain.interactor.GetAreasUseCaseImplementation

val filtrationInteractorModule = module {
    factory<GetAreasUseCase> {
        GetAreasUseCaseImplementation(get(), get())
    }
}
