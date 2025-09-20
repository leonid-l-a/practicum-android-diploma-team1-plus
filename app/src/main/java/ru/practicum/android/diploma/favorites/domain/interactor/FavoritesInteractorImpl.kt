package ru.practicum.android.diploma.favorites.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository
) : FavoritesInteractor {
    override fun getFavoritesVacancy(): Flow<List<VacancyFavorites?>> {
        return favoritesRepository.favoritesVacancy()
    }

    override suspend fun addFavoriteVacancy(vacancy: VacancyDetail): Long {
        return favoritesRepository.addFavorite(vacancy)
    }

    override suspend fun findFavoriteVacancy(id: String): VacancyFavorites? {
        return favoritesRepository.findFavorite(id)
    }

    override suspend fun deleteFromFavorites(vacancy: VacancyDetail) {
        favoritesRepository.deleteFromFavorites(vacancy)
    }
}
