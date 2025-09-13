package ru.practicum.android.diploma.filtration.di.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.filtration.ui.viewmodel.AreasViewModel

val filtrationViewModelModule = module {
    viewModel { AreasViewModel(get()) }
}
