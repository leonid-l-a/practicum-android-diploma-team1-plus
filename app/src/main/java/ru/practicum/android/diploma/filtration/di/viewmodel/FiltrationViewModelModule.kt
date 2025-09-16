package ru.practicum.android.diploma.filtration.di.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.filtration.ui.viewmodel.CountrySelectionViewModel
import ru.practicum.android.diploma.filtration.ui.viewmodel.IndustriesViewModel
import ru.practicum.android.diploma.filtration.ui.viewmodel.IndustryViewModel
import ru.practicum.android.diploma.filtration.ui.viewmodel.RegionSelectionViewModel
import ru.practicum.android.diploma.filtration.ui.viewmodel.WorkPlaceViewModel

val filtrationViewModelModule = module {
    viewModel {
        IndustryViewModel(
            industriesInteractor = get(),
            appInteractor = get()
        )
    }
    viewModel {
        MainFilterViewModel(
            appInteractor = get()
        )
    }
    viewModel { WorkPlaceViewModel() }
    viewModel { CountrySelectionViewModel(get()) }
    viewModel { RegionSelectionViewModel(get()) }
}
