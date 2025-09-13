package ru.practicum.android.diploma.favorites.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.domain.interactor.FavoritesInteractor
import ru.practicum.android.diploma.favorites.domain.interactor.FavoritesInteractorImpl

val favoriteInteractorModule = module {
    single<FavoritesInteractor> {
        FavoritesInteractorImpl(get())
    }
}
