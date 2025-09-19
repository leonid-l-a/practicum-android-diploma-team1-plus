package ru.practicum.android.diploma.favorites.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.core.data.dto.VacancyDetailDto
import ru.practicum.android.diploma.favorites.data.db.dao.FavoritesDao
import ru.practicum.android.diploma.favorites.data.mapper.VacancyDbMapper
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository {

    override fun favoritesVacancy(): Flow<List<VacancyFavorites?>> {
        return favoritesDao.getAllFavoritesVacancy().map { list ->
            list.map { VacancyDbMapper.favoritesEntityToVacancyFavorites(it) }
        }
    }

    override suspend fun addFavorite(vacancy: VacancyDetailDto): Long {
        return favoritesDao.insertFavoriteVacancy(
            VacancyDbMapper.vacancyDetailToFavoritesEntity(vacancy)
        )
    }

    override suspend fun findFavorite(vacancy: VacancyDetailDto): VacancyFavorites? {
        val entity = favoritesDao.findById(vacancy.id)
        return VacancyDbMapper.favoritesEntityToVacancyFavorites(entity)
    }

    override suspend fun deleteFromFavorites(vacancy: VacancyDetailDto) {
        favoritesDao.deleteById(vacancy.id)
    }
}
