package ru.practicum.android.diploma.favorites.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
import ru.practicum.android.diploma.favorites.data.mapper.VacancyDbMapper
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository
) : FavoritesInteractor {
    override fun getFavoritesVacancy(): Flow<List<VacancyFavorites?>> {
        return favoritesRepository.favoritesVacancy()
    }

    override suspend fun addFavoriteVacancy(vacancy: VacancyDetail): Long {
        return favoritesRepository.addFavorite(vacancy)
    }

    override suspend fun findFavoriteVacancy(vacancy: VacancyDetail): VacancyDetail? {
        return VacancyDbMapper.vacancyFavoritesToVacancyDetail(
            favoritesRepository.findFavorite(
                vacancy
            )
        )
    }

    override suspend fun deleteFromFavorites(vacancy: VacancyDetail) {
        favoritesRepository.deleteFromFavorites(vacancy)
    }
}
