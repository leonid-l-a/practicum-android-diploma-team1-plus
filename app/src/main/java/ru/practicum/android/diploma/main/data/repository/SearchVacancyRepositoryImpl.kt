package ru.practicum.android.diploma.main.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.data.utils.ResponseCode
import ru.practicum.android.diploma.main.domain.model.Resource
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository

class SearchVacancyRepositoryImpl(
    val networkClient: VacancyNetworkClient
) : SearchVacancyRepository {

    companion object {
        const val CONNECTION_PROBLEMS = "Нет интернета"
    }

    override fun searchVacancy(vacancyRequest: VacancyRequest): Flow<Resource<Vacancy>> =
        flow {
            val networkResponse = networkClient.getVacancies(vacancyRequest)

            when(networkResponse.resultCode){
                ResponseCode.SUCCESS -> {
                    emit(Resource.Success(
                        data = networkResponse.vacancy)
                    )
                }
                ResponseCode.IO_ERROR -> {
                    emit(Resource.Error(
                        message = CONNECTION_PROBLEMS
                    ))
                }
            }
        }
}
