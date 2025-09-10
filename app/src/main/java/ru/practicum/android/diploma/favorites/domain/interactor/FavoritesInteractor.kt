package ru.practicum.android.diploma.favorites.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites

interface FavoritesInteractor {

    fun getFavoritesVacancy(): Flow<List<VacancyFavorites?>>

    suspend fun addFavoriteVacancy(vacancy: VacancyDetail): Long

    suspend fun findFavoriteVacancy(vacancy: VacancyDetail): VacancyDetail?

    suspend fun deleteFromFavorites(vacancy: VacancyDetail)
}
