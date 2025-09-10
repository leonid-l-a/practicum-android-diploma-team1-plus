package ru.practicum.android.diploma.main.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.data.utils.ResponseCode
import ru.practicum.android.diploma.main.data.mapper.VacancyMapper
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.repository.SearchVacancyRepository
import ru.practicum.android.diploma.main.domain.state.Resource
import ru.practicum.android.diploma.util.NetworkUtil

class SearchVacancyRepositoryImpl(
    val networkClient: VacancyNetworkClient,
    val networkUtil: NetworkUtil,
    val context: Context
) : SearchVacancyRepository {

    override fun searchVacancy(
        expression: String,
        page: Int
    ): Flow<Resource<VacancyMainData>> =
        flow {
            if (!networkUtil.isInternetAvailable(context)) {
                emit(
                    Resource.Error()
                )
            } else {
                val vacancyRequest = VacancyRequest(
                    text = expression,
                    page = page
                )

                val networkResponse = networkClient.getVacancies(vacancyRequest)

                when (networkResponse.resultCode) {
                    ResponseCode.SUCCESS -> {
                        emit(
                            Resource.Success(
                                data = VacancyMapper.vacancyToVacancyMainData(
                                    vacancy = networkResponse.vacancy,
                                )
                            )
                        )
                    }

                    ResponseCode.IO_ERROR -> {
                        emit(
                            Resource.Error()
                        )
                    }
                }
            }
        }
}
