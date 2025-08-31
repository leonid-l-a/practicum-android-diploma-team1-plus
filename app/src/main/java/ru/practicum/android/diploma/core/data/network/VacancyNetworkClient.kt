package ru.practicum.android.diploma.core.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.core.data.dto.Responce
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import kotlin.apply

class VacancyNetworkClient(
    private val apiService: VacancyApiService
) {

    suspend fun getFilterAreas(): Responce {
        return withContext(Dispatchers.IO) {
            try {
                val resp = apiService.getFilterAreas()
                resp.apply { resultCode = 200 }
            } catch (e: Exception) {
                Responce().apply { resultCode = 500 }
            }
        }
    }

    suspend fun getFilterIndustries(): Responce {
        return withContext(Dispatchers.IO) {
            try {
                val resp = apiService.getFilterIndustries()
                resp.apply { resultCode = 200 }
            } catch (e: Exception) {
                Responce().apply { resultCode = 500 }
            }
        }
    }

    suspend fun getVacancies(request: VacancyRequest): Responce {
        return withContext(Dispatchers.IO) {
            try {
                val resp = apiService.getVacancies(request.toQueryMap())
                resp.apply { resultCode = 200 }
            } catch (e: Exception) {
                Responce().apply { resultCode = 500 }
            }
        }
    }

    suspend fun getVacancy(id: Int): Responce {
        return withContext(Dispatchers.IO) {
            try {
                val resp = apiService.getVacancy(id)
                resp.apply { resultCode = 200 }
            } catch (e: Exception) {
                Responce().apply { resultCode = 500 }
            }
        }
    }
}

