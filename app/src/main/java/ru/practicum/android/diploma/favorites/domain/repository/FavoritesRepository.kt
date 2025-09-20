package ru.practicum.android.diploma.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail

interface FavoritesRepository {

    fun favoritesVacancy(): Flow<List<VacancyFavorites?>>

    suspend fun addFavorite(vacancy: VacancyDetail): Long

    suspend fun findFavorite(id: String): VacancyFavorites?

    suspend fun deleteFromFavorites(vacancy: VacancyDetail)
}
