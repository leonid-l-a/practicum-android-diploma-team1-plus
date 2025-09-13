package ru.practicum.android.diploma.filtration.di.data

import org.koin.dsl.module
import ru.practicum.android.diploma.filtration.data.repository.AreaRepositoryImplementation
import ru.practicum.android.diploma.filtration.domain.repository.AreaRepository

val filtrationDataModule = module {
    single<AreaRepository> {
        AreaRepositoryImplementation(get())
    }
}
