package ru.practicum.android.diploma.favorites.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail

interface FavoritesInteractor {

    fun getFavoritesVacancy(): Flow<List<VacancyFavorites?>>

    suspend fun addFavoriteVacancy(vacancy: VacancyDetail): Long

    suspend fun findFavoriteVacancy(vacancy: VacancyDetail): VacancyDetail?

    suspend fun deleteFromFavorites(vacancy: VacancyDetail)
}
