package ru.practicum.android.diploma.main.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.data.utils.ResponseCode
import ru.practicum.android.diploma.main.data.mapper.VacancyToVacancyMainData
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository
import ru.practicum.android.diploma.main.domain.state.Resource
import ru.practicum.android.diploma.util.NetworkUtil

class SearchVacancyRepositoryImpl(
    val networkClient: VacancyNetworkClient,
    val networkUtil: NetworkUtil,
    val context: Context
) : SearchVacancyRepository {

    companion object {
        const val CONNECTION_PROBLEMS = "Нет интернета"
    }

    override fun searchVacancy(expression: String): Flow<Resource<VacancyMainData>> =
        flow {

            if (!networkUtil.isInternetAvailable(context)) {

                emit(
                    Resource.Error(
                        message = CONNECTION_PROBLEMS
                    )
                )
            } else {

                val vacancyRequest = VacancyRequest(
                    text = expression
                )

                val networkResponse = networkClient.getVacancies(vacancyRequest)

                when (networkResponse.resultCode) {
                    ResponseCode.SUCCESS -> {

                        emit(
                            Resource.Success(
                                data = VacancyToVacancyMainData.vacancyToVacancyMainData(vacancy = networkResponse.vacancy)
                            )
                        )
                    }

                    ResponseCode.IO_ERROR -> {
                        emit(
                            Resource.Error(
                                message = CONNECTION_PROBLEMS
                            )
                        )
                    }
                }
            }
        }
}
