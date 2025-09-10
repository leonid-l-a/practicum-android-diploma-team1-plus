package ru.practicum.android.diploma.favorites.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
import ru.practicum.android.diploma.favorites.data.db.dao.FavoritesDao
import ru.practicum.android.diploma.favorites.data.mapper.VacancyDbMapper
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository {
    override fun favoritesVacancy(): Flow<List<VacancyFavorites?>> {
        return favoritesDao.getAllFavoritesVacancy().map { favoritesVacancy ->
            favoritesVacancy
                .map { VacancyDbMapper.favoritesEntityToVacancyFavorites(it) }
        }
    }

    override suspend fun addFavorite(vacancy: VacancyDetail): Long {
        return favoritesDao.insertFavoriteVacancy(
            VacancyDbMapper.vacancyDetailToFavoritesEntity(vacancy)
        )
    }

    override suspend fun findFavorite(vacancy: VacancyDetail): VacancyFavorites? {
        return VacancyDbMapper.favoritesEntityToVacancyFavorites(favoritesDao.findById(vacancy.id))
    }

    override suspend fun deleteFromFavorites(vacancy: VacancyDetail) {
        favoritesDao.deleteById(vacancy.id)
    }
}
