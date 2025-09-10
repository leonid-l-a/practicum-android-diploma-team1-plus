package ru.practicum.android.diploma.favorites.di.domain

import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.data.repository.FavoritesRepositoryImpl
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

val favoriteRepositoryModule = module {
    single<FavoritesRepository>{
        FavoritesRepositoryImpl(get())
    }
}
