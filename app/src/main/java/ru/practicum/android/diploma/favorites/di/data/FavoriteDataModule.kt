package ru.practicum.android.diploma.favorites.di.data

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.data.db.AppDataBase

val favoriteDataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            "favoritesVacancy.db"
        )
            .fallbackToDestructiveMigration().build()
    }

    single { get<AppDataBase>().favoritesDao() }
}
