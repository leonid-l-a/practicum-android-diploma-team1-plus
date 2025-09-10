package ru.practicum.android.diploma.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites

interface FavoritesRepository {

    fun favoritesVacancy(): Flow<List<VacancyFavorites?>>

    suspend fun addFavorite(vacancy: VacancyDetail): Long

    suspend fun findFavorite(vacancy: VacancyDetail): VacancyFavorites?

    suspend fun deleteFromFavorites(vacancy: VacancyDetail)
}
