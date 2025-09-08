package ru.practicum.android.diploma.favorites.di.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoritesViewModel

val favoriteViewModelModule = module {
    viewModel { FavoritesViewModel() }
}
