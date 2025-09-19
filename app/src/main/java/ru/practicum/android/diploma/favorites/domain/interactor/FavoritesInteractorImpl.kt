package ru.practicum.android.diploma.favorites.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.data.mapper.VacancyDbMapper
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.favorites.domain.toDetail
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository
) : FavoritesInteractor {

    override fun getFavoritesVacancy(): Flow<List<VacancyFavorites?>> {
        return favoritesRepository.favoritesVacancy()
    }

    override suspend fun addFavoriteVacancy(vacancy: VacancyDetail): Long {
        val dto = VacancyDbMapper.detailToDto(vacancy)
        return favoritesRepository.addFavorite(dto)
    }

    override suspend fun findFavoriteVacancy(vacancy: VacancyDetail): VacancyDetail? {
        val dto = VacancyDbMapper.detailToDto(vacancy)
        val favorite = favoritesRepository.findFavorite(dto)
        return favorite?.toDetail()
    }

    override suspend fun deleteFromFavorites(vacancy: VacancyDetail) {
        val dto = VacancyDbMapper.detailToDto(vacancy)
        favoritesRepository.deleteFromFavorites(dto)
    }
}
