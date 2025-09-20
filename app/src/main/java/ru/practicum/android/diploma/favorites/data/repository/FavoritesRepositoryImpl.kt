package ru.practicum.android.diploma.favorites.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.favorites.data.db.dao.FavoritesDao
import ru.practicum.android.diploma.favorites.data.mapper.VacancyDbMapper
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail

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

    override suspend fun findFavorite(id: String): VacancyFavorites? {
        return VacancyDbMapper.favoritesEntityToVacancyFavorites(favoritesDao.findById(id))
    }

    override suspend fun deleteFromFavorites(vacancy: VacancyDetail) {
        favoritesDao.deleteById(vacancy.id)
    }
}
