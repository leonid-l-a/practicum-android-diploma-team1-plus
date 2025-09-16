package ru.practicum.android.diploma.filtration.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.data.utils.ResponseCode
import ru.practicum.android.diploma.filtration.data.mapper.IndustriesMapper
import ru.practicum.android.diploma.filtration.domain.model.Industries
import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail
import ru.practicum.android.diploma.filtration.domain.model.StorageResultCode
import ru.practicum.android.diploma.filtration.domain.repository.IndustriesRepository
import ru.practicum.android.diploma.filtration.domain.state.Resource
import ru.practicum.android.diploma.util.NetworkUtil

class IndustriesRepositoryImpl(
    val networkClient: VacancyNetworkClient,
    val networkUtil: NetworkUtil,
    val context: Context
) : IndustriesRepository {
    private fun initIndustries(
        typeError: StorageResultCode,
        items: List<IndustryDetail> = emptyList(),
    ): Industries {
        return Industries(
            items = items,
            resultCode = StorageResultCode.SERVER_ERROR,
        )
    }

    override suspend fun getIndustries(): Flow<Resource<Industries>> {
        return flow {
            if (!networkUtil.isInternetAvailable(context)) {
                emit(
                    Resource.Error(
                        initIndustries(typeError = StorageResultCode.CLIENT_ERROR)
                    )
                )
            } else {
                val response = networkClient.getFilterIndustries()
                when (response.resultCode) {
                    ResponseCode.SUCCESS -> {
                        val items =
                        emit(
                            Resource.Success(
                                IndustriesMapper.toIndustries(
                                    data = networkClient.getFilterIndustries(),
                                )
                            )
                        )
                    }

                    ResponseCode.IO_ERROR -> emit(
                        Resource.Error(
                            initIndustries(typeError = StorageResultCode.SERVER_ERROR)
                        )
                    )

                    else -> emit(
                        Resource.Error(
                            initIndustries(typeError = StorageResultCode.SERVER_ERROR)
                        )
                    )
                }
            }
        }
    }
}
