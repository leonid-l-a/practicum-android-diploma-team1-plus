package ru.practicum.android.diploma.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.VacancyDetailDto
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites

interface FavoritesRepository {

    fun favoritesVacancy(): Flow<List<VacancyFavorites?>>

    suspend fun addFavorite(vacancy: VacancyDetailDto): Long

    suspend fun findFavorite(vacancy: VacancyDetailDto): VacancyFavorites?

    suspend fun deleteFromFavorites(vacancy: VacancyDetailDto)
}
